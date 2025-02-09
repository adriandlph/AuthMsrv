package com.adriandlph.auth.logic;

import com.adriandlph.msrv.data.model.ValidationResult;
import com.adriandlph.auth.data.entity.User;
import com.adriandlph.auth.data.model.UserModel;
import java.util.regex.Pattern;

/**
 *
 * @author adriandlph
 * 
 */
public class UsersUtils {
	
	/**
	 * Parse user to its model.
	 * 
	 * Model will contain: id, username, email, name and last name of the user.
	 * 
	 * @param user User
	 * 
	 * @return user model.
	 */
	public static UserModel User2UserModel(User user) {
		if (user == null) return null;
		
		UserModel userModel;
		
		userModel = new UserModel();
		userModel.setId(user.getId());
		userModel.setUsername(user.getUsername());
		userModel.setEmail(user.getEmail());
		userModel.setName(user.getName());
		userModel.setLastName(user.getLastName());
		
		return userModel;
	}
	
	/**
	 * Validates an username.
	 * 
	 * An username must be defined (not null and not blank), and can only 
	 * contain letters and numbers.
	 * 
	 * @param username Username that will be validated.
	 * 
	 * @return a validation result.
	 */
	public static ValidationResult IsValidUsername(String username) {
		if (username == null) return ValidationResult.notValid("Username not defined.");
		if (username.isBlank()) return ValidationResult.notValid("Username not defined.");
		
		if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
			return ValidationResult.notValid("Username can only contain letters and numbers.");
		}
		
		return ValidationResult.valid();
	}
	
	public static ValidationResult IsValidPassword(String password) {
		if (password == null) return ValidationResult.notValid("Password not defined.");
		if (password.isBlank()) return ValidationResult.notValid("Password not defined.");
		
		if (!Pattern.matches("[a-zA-Z0-9.!{}<>-_$#%^&*+()=@?/]+", password)) {
			return ValidationResult.notValid("Password can only contain letters, numbers and .!{}<>-_$#%^&*+()=@?/");
		}
		
		return ValidationResult.valid();
	}
	
	public static ValidationResult IsValidEmail(String email) {
		String[] parts;
		
		if (email == null) return ValidationResult.notValid("Email not defined.");
		if (email.isBlank()) return ValidationResult.notValid("Email not defined.");
		
		parts = email.split("@");
		if (parts.length != 2) return ValidationResult.notValid("Email not valid");
		if (!parts[1].contains(".")) return ValidationResult.notValid("Email not valid");
		
		return ValidationResult.valid();
	}
	
	public static ValidationResult IsValidName(String name) {
		if (name == null) return ValidationResult.notValid("Name not defined.");
		if (name.isBlank()) return ValidationResult.notValid("Name not defined.");
		
		if (!Pattern.matches("[a-zA-Z\s]+", name)) {
			return ValidationResult.notValid("Name can only contain letters, numbers and spaces.");
		}
		
		return ValidationResult.valid();
	}
	
	public static ValidationResult IsValidLastName(String lastName) {
		if (lastName == null) return ValidationResult.notValid("Last name not defined.");
		if (lastName.isBlank()) return ValidationResult.notValid("Last name not defined.");
		
		if (!Pattern.matches("[a-zA-Z\s]+", lastName)) return ValidationResult.notValid("Last name can only contain letters and spaces.");
		
		return ValidationResult.valid();
	}
	
}
