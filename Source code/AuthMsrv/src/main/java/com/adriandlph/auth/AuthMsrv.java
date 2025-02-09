package com.adriandlph.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author adriandlph
 * 
 */
@SpringBootApplication
public class AuthMsrv {	
	private static final Logger LOG = LoggerFactory.getLogger(AuthMsrv.class);
	
	public static void main(String[] args) {
		LOG.info("Starting AuthMsrv...");
		SpringApplication.run(AuthMsrv.class, args);
	}
}
