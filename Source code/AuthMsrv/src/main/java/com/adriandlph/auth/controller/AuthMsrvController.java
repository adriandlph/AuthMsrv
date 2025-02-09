package com.adriandlph.auth.controller;

import com.adriandlph.auth.App;
import com.adriandlph.msrv.data.model.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adriandlph
 * 
 */
@RestController
@RequestMapping(path = App.BASE_PATH)
public class AuthMsrvController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthMsrvController.class);
	
	// Properties
	@Value("${key.public.fileName:#{null}}")
	public String pubKeyFile;
	@Value("${key.private.fileName:#{null}}")
	public String privKeyFile;
	
	
    @RequestMapping(path = "/helloWorld")
	public Response<String> helloWorld() {
		return new Response<>(0, "Ok", "Hello World");
	}
	
	@GetMapping(path = "/publicKey")
	public Response<String> getPublicKey() {
		byte[] key = getMicroservicePublicKey();		
		
		if (key == null) return new Response<>(1, "Server error", "");
		return Response.Ok(new String(key));
	}
	
	
	public byte[] getMicroservicePublicKey() {
		File file;
		
		if (pubKeyFile == null) return null;
		
		// Check public key file exists
		file = new File(pubKeyFile);
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		
		// Get key bytes
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException ex) {
			LOG.error("Error reading public key file: {}", ex.toString());
			return null;
		}
	}
	
}
