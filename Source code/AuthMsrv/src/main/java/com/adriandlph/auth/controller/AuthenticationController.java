package com.adriandlph.auth.controller;

import com.adriandlph.auth.App;
import com.adriandlph.auth.exception.authentication.LoginException;
import com.adriandlph.auth.logic.Authentications;
import com.adriandlph.auth.data.model.UserModel;
import com.adriandlph.msrv.data.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adriandlph
 * 
 */
@RestController
@RequestMapping(path = App.BASE_PATH + "/")
public class AuthenticationController {
	@Autowired
	private Authentications authentications;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public Response<String> login(@RequestBody UserModel userModel) {
		String token;
		
		try {
			token = authentications.loginUser(userModel);
		} catch (LoginException ex) {
			return new Response<>(ex.getCode(), ex.getMessage(), null);
		}
		
		return Response.Ok(token);
	}
	
	
}
