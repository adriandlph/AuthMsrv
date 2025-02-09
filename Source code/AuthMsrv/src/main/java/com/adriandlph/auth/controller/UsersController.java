package com.adriandlph.auth.controller;

import com.adriandlph.auth.App;
import com.adriandlph.auth.data.entity.User;
import com.adriandlph.auth.exception.user.CreateUserException;
import com.adriandlph.auth.exception.user.DeleteUserException;
import com.adriandlph.auth.exception.user.EditUserException;
import com.adriandlph.auth.logic.Users;
import com.adriandlph.auth.logic.UsersUtils;
import com.adriandlph.auth.data.model.UserModel;
import com.adriandlph.msrv.data.model.Response;
import com.adriandlph.msrv.exception.NotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adriandlph
 * 
 * For information about these methods, check documentation of the API.
 * 
 */
@RestController
@RequestMapping(path = App.BASE_PATH + "/user")
public class UsersController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthMsrvController.class);
	
	@Autowired
	private Users users;
	
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public Response<UserModel> getUserById(@PathVariable(name = "id") String userIdStr) {
		User user;
		Long userId;
		
		try {
			userId = Long.valueOf(userIdStr);
		} catch (NullPointerException | NumberFormatException ex) {
			userId = null;
		}
		
		if (userId == null) return Response.Ok(null);
		
		user = users.getUserById(userId);
		
		return Response.Ok(UsersUtils.User2UserModel(user));
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Response<UserModel> getUserByUsername(@RequestParam(name = "username") String username) {
		User user;
		
		if (username == null) return Response.Ok(null);
		
		user = users.getUserByUsername(username);
		
		return Response.Ok(UsersUtils.User2UserModel(user));
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Response<UserModel> createUser(@RequestBody UserModel userModel) {
		User user;
		
		try {
			user = users.createUser(userModel);	
		} catch (NotValidException | CreateUserException ex) {
			return new Response<>(ex.getCode(), ex.getMessage(), null);
		}
		
		return Response.Ok(UsersUtils.User2UserModel(user));
	}
	
	@RequestMapping(path = "", method = RequestMethod.PUT)
	public Response<UserModel> editUser(@RequestBody UserModel userModel) {
		User user;
		
		try {
			user = users.editUser(userModel);			
		} catch (NotValidException | EditUserException ex) {
			return new Response<>(ex.getCode(), ex.getMessage(), null);
		}
		
		return Response.Ok(UsersUtils.User2UserModel(user));
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public Response<Void> deleteUser(@PathVariable(name = "id") String userIdStr) {
		Long userId;
		
		try {
			userId = Long.valueOf(userIdStr);
		} catch (NullPointerException | NumberFormatException ex) {
			userId = null;
		}
		
		try {
			users.deleteUser(userId);
		} catch (DeleteUserException ex) {
			return new Response<>(ex.getCode(), ex.getMessage(), null);
		}
		
		return Response.Ok(null);
	}
	
}
