package com.adriandlph.auth.logic;

import com.adriandlph.auth.data.entity.User;
import com.adriandlph.auth.exception.authentication.TokenException;
import com.adriandlph.auth.data.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;

/**
 *
 * @author adriandlph
 * 
 */
public class JWTUtils {	
	public static UserModel Token2UserModel(DecodedJWT decodedToken) {
		UserModel userModel;
		
		userModel = new UserModel();
		
		userModel.setId(decodedToken.getClaim("userId").asLong());
		userModel.setUsername(decodedToken.getClaim("username").asString());
		
		return userModel;
	}
	
}
