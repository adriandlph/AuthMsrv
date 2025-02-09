package com.adriandlph.auth.logic;

import com.adriandlph.msrv.data.model.ValidationResult;
import com.adriandlph.auth.data.entity.User;
import com.adriandlph.auth.exception.user.CreateUserException;
import com.adriandlph.auth.exception.user.DeleteUserException;
import com.adriandlph.auth.exception.user.EditUserException;
import com.adriandlph.auth.data.model.UserModel;
import com.adriandlph.msrv.exception.NotValidException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;

/**
 *
 * @author adriandlph
 * 
 */
@Controller
public class Users {
	@PersistenceContext ( type = PersistenceContextType.EXTENDED )
	private EntityManager em;
	
	public Users() {
	}
	
	/**
	 * Create a user.
	 * 
	 * The userModel must contain a valid: username, password and email. Name 
	 * and last name are optional but if they are defined, they must be valid.
	 * If name is not defined, last name will not be used.
	 * 
	 * @param userModel user data.
	 * 
	 * @return the created user.
	 * 
	 * @throws NotValidException Any of the model data is not valid.
	 * @throws CreateUserException An error occurred while creating the user.
	 */
	@Transactional
	public User createUser(UserModel userModel) throws NotValidException, CreateUserException {
		User user;
		Query query;
		Long count;
		ValidationResult validationResult;
		
		if (userModel == null) throw new NotValidException(1, "User not defined.");
		
		// Username validation
		if (!(validationResult = UsersUtils.IsValidUsername(userModel.getUsername())).isValid()) {
			throw new NotValidException(2, validationResult.getReason());
		}
		
		// Password validation
		if (!(validationResult = UsersUtils.IsValidPassword(userModel.getPassword())).isValid()) {
			throw new NotValidException(3, validationResult.getReason());
		}
		
		// Email validation
		if (!(validationResult = UsersUtils.IsValidEmail(userModel.getEmail())).isValid()) {
			throw new NotValidException(4, validationResult.getReason());
		}
		
		// Name validation
		if ((userModel.getName() != null) && (!userModel.getName().isBlank())) {
			
			if (!(validationResult = UsersUtils.IsValidName(userModel.getName())).isValid()) {
				throw new NotValidException(5, validationResult.getReason());
			}
			
			
			// Last name validation
			if ((userModel.getLastName() != null) && (!userModel.getLastName().isBlank())) {
				if (!(validationResult = UsersUtils.IsValidLastName(userModel.getLastName())).isValid()) {
					throw new NotValidException(6, validationResult.getReason());
				}
			} else {
				userModel.setLastName(null);
			}
			
		} else {
			userModel.setName(null);
			userModel.setLastName(null);
		}
		
		// Check if username already exists or email is already registered
		try {
			query = em.createQuery("SELECT COUNT(usr.id) FROM User usr WHERE usr.email = :email");
			query.setParameter("email", userModel.getEmail());
			count = (Long)query.getSingleResult();
			if (count == null || count > 0) {
				throw new NotValidException(10, "Email already registered");
			}
			
			query = em.createQuery("SELECT COUNT(usr.id) FROM User usr WHERE usr.username = :username");
			query.setParameter("username", userModel.getUsername());
			count = (Long)query.getSingleResult();
			if (count == null || count > 0) {
				throw new NotValidException(11, "Username already exists");
			}
			
		} catch (NotValidException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CreateUserException(ex.getMessage());
		}
		
		// Create user
		user = new User();
		user.setUsername(userModel.getUsername());
		user.setPassword(userModel.getPassword());
		user.setEmail(userModel.getEmail());
		user.setName(userModel.getName());
		user.setLastName(userModel.getLastName());
		
		// Persists the new user
		em.persist(user);
		em.flush();
		
		// Return the created user
		return user;
	}
	
	/**
	 * Gets the user that has the id specified.
	 * 
	 * @param userId Id of the user.
	 * 
	 * @return the user data.
	 */
	public User getUserById(Long userId) {	
		// Checks if user id has been specified.
		if (userId == null) return null;
		
		// Gets the user from BD
		return em.find(User.class, userId);
	}
	
	/**
	 * Gets the user that has the username specified.
	 * 
	 * @param username Username of the user.
	 * 
	 * @return the user data.
	 */
	public User getUserByUsername(String username) {
		User user;
		Query query;
		
		// Check if username has been defined
		if (username == null) return null;
		
		// Gets user from DB
		try {
			query = em.createQuery("SELECT usr FROM User usr WHERE usr.username = :username");
			query.setParameter("username", username);
			user = (User)query.getSingleResult();
		} catch (Exception ex) {
			user = null;
		}
		
		return user;
	}
	
	/**
	 * Edits user data (username, name, last name). 
	 * 
	 * To edit password or email, check editUserPassword() and editUserEmail() 
	 * methods.
	 * 
	 * User name or lastName as null == don't modify.
	 * User name or lastName as blank == remove data and set as null.
	 * 
	 * @param userModel new user data. If null, 
	 * 
	 * @return the edited user.
	 * 
	 * @throws NotValidException Any of the model data is not valid.
	 * @throws EditUserException An error occurred while editing the user.
	 */
	@Transactional
	public User editUser(UserModel userModel) throws NotValidException, EditUserException {
		User user;
		ValidationResult validationResult;
		
		if (userModel.getId() == null) {
			throw new EditUserException(1, "User id not defined.");
		}
		
		user = em.find(User.class, userModel.getId());
		if (user == null) {
			throw new EditUserException(1, "User not found.");
		}
		
		// If username changed -> validate and update
		if ((userModel.getUsername() != null) && (!userModel.getUsername().equals(user.getUsername()))) {
			if (!(validationResult = UsersUtils.IsValidUsername(userModel.getUsername())).isValid()) {
				throw new NotValidException(2, validationResult.getReason());
			}
			user.setUsername(userModel.getUsername());
		}
		
		// If name changed -> validate and update
		if ((userModel.getName() != null) && (!userModel.getName().equals(user.getName()))) {
			if (userModel.getName().isBlank()) {
				// Blank == remove name data
				userModel.setName(null);
			} else if (!(validationResult = UsersUtils.IsValidName(userModel.getName())).isValid()) {
				throw new NotValidException(3, validationResult.getReason());
			}
			user.setName(userModel.getName());
		}
		
		// If name is defined and last name changed -> validate and update
		if ((user.getName() != null) && (userModel.getLastName() != null) && (!userModel.getLastName().equals(user.getLastName()))) {
			if (userModel.getLastName().isBlank()) {
				// Blank == remove last name data
				userModel.setLastName(null);
			} else if (!(validationResult = UsersUtils.IsValidLastName(userModel.getLastName())).isValid()) {
				throw new NotValidException(4, validationResult.getReason());
			}
			user.setLastName(userModel.getLastName());
		}
		
		return user;
	}
	
	/**
	 * Delete user with the specified id.
	 * 
	 * @param userId id of the user.
	 * 
	 * @throws DeleteUserException An error occurred while deleting the user.
	 */
	@Transactional
	public void deleteUser(Long userId) throws DeleteUserException {
		User user;
		
		// Validate user id
		if (userId == null) {
			throw new DeleteUserException(1, "user id is not defined or has wrong format.");
		}
		
		// Gets user
		user = em.find(User.class, userId);
		if (user == null) {
			throw new DeleteUserException(2, "User not found.");
		}
		
		// Remove user and flush
		em.remove(user);
		em.flush();
	}
	
}
