package com.adriandlph.auth.logic;

import com.adriandlph.msrv.data.model.ValidationResult;
import com.adriandlph.auth.data.entity.Event;
import com.adriandlph.auth.data.entity.Subscriber;
import com.adriandlph.auth.data.entity.Token;
import com.adriandlph.auth.data.entity.User;
import com.adriandlph.auth.data.enumeration.EventType;
import com.adriandlph.auth.data.enumeration.TokenType;
import com.adriandlph.auth.exception.authentication.LoginException;
import com.adriandlph.auth.exception.authentication.LogoutException;
import com.adriandlph.auth.data.model.UserModel;
import com.adriandlph.encryption.algorithms.RSA;
import com.adriandlph.encryption.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 *
 * @author adriandlph
 * 
 */
@Controller
public class Authentications {
	// Spring
	@PersistenceContext ( type = PersistenceContextType.EXTENDED )
	private EntityManager em;
	
	@Value("${login.token.duration:3600}")
	public static int LOGIN_TOKEN_DURATION_SECONDS;
	@Value("${login.token.issuer:MsrvAuth}")
	public static String LOGIN_TOKEN_ISSUER;
	@Value("${login.token.subject:LOGIN_TOKEN}")
	public static String LOGIN_TOKEN_SUBJECT;
	@Value("${key.public.fileName:#{null}}")
	public String pubKeyFile;
	@Value("${key.private.fileName:#{null}}")
	public String privKeyFile;
	
	// Attributes
	private static List<String> tokenBlacklist;
	private static Semaphore tokenBlacklistSem;
	
	public Authentications() {
		if (tokenBlacklist == null || tokenBlacklistSem == null) {
			tokenBlacklist = new ArrayList<>();
			tokenBlacklistSem = new Semaphore(1);
		}
	}
	
	@Transactional
	public String loginUser(UserModel userModel) throws LoginException {
		Token token;
		String tokenStr;
		ValidationResult validationResult;
		Query query;
		User user;
		Calendar issuedAt, expires;
		
		// Validate input (user, username and password)
		if (userModel == null) throw new LoginException(1, "User data not defined");
		
		if (!(validationResult = UsersUtils.IsValidUsername(userModel.getUsername())).isValid()) {
			throw new LoginException(2, validationResult.getReason());
		}
		
		if (!(validationResult = UsersUtils.IsValidPassword(userModel.getPassword())).isValid()) {
			throw new LoginException(3, validationResult.getReason());
		}
		
		// Check if username and password are correct
		try {
			query = em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password");
			query.setParameter("username", userModel.getUsername());
			query.setParameter("password", userModel.getPassword());
			user = (User)query.getSingleResult();
			
		} catch (Exception ex) {
			throw new LoginException(4, "Username or password not valid");
		}
		
		
		// Generates a JWT
		Map<String,Object> claims = new HashMap<>();
		claims.put("userId", user.getId());
		claims.put("userUsername", user.getUsername());
		issuedAt = Calendar.getInstance();
		expires = (Calendar)issuedAt.clone();
		expires.add(Calendar.SECOND, LOGIN_TOKEN_DURATION_SECONDS);

		try {
			tokenStr = JWT.generateToken(getAuthenticationAlgorithm(), 
				LOGIN_TOKEN_SUBJECT, 
				LOGIN_TOKEN_ISSUER, 
				issuedAt, 
				expires, 
				claims);
		} catch (IllegalArgumentException | JWTCreationException ex) {
			System.err.println("Error generating authentication JWT: " + ex.toString());
			throw new LoginException(5, "Unexpected error.");
		}
			
		
		// Creates token
		token = new Token();
		token.setType(TokenType.AUTHENTICATION);
		token.setToken(tokenStr);
		token.setUser(user);
		
		// Perist token
		em.persist(token);
		em.flush();
			
		return token.getToken();
	}
	
	@Transactional
	public void logoutUser(String tokenStr) throws LogoutException {
		Token token;
		Query query;
		
		// Search token
		query = em.createQuery("SELECT t FROM Token t WHERE t.token = :tokenStr");
		query.setParameter("tokenStr", tokenStr);
		try {
			token = (Token)query.getSingleResult();
			em.remove(token);
		} catch (NoResultException ex) {
			// Continue the logout to avoid other microservices process that token
			System.err.println("Token not found: " + tokenStr);
		} catch (Exception ex) {
			// Logout not completed
			System.err.println("Exception: " + ex.toString());
			throw new LogoutException(1, "Unexpected error.");
		}
		
		Event event = new Event();
		event.setId(0L);
		event.setEventType(EventType.USER_LOGOUT);
		event.setArguments("token=" + tokenStr);
		event.setGeneratedAt(Calendar.getInstance());
		event.setProcessed(Boolean.FALSE);
		em.persist(event);
		em.flush();
	}
	
	// -----------------------------
	
	public boolean addTokenToBlacklist(String token) {
		try {
			tokenBlacklistSem.acquire();
		} catch (InterruptedException ex) {
			return false;
		}
		
		tokenBlacklist.add(token);
		
		tokenBlacklistSem.release();
		return true;
	}
	
	public boolean removeTokenToBlacklist(String token) {
		return false;
	}
	
	/**
	 * Force this microservice to update its token blacklist until token expires
	 */
	public void updateBlacklist() {
		
	}
	
	public void logoutUser() {		
		Query query;
		List<Subscriber> subscribers;
		
		query = em.createQuery("SELECT s FROM Subscriber s WHERE s.eventType = :eventType");
		query.setParameter("eventType", EventType.USER_LOGOUT);
		subscribers = query.getResultList();
		
		// Send logout event
		
		
	}
	
	
	// -----------------------------
	
	public Algorithm getAuthenticationAlgorithm() {
		KeyPair keyPair = RSA.loadKeyPair(pubKeyFile, privKeyFile);
		return RSA.getRSAAlgorithm(keyPair);
	}
	
}
