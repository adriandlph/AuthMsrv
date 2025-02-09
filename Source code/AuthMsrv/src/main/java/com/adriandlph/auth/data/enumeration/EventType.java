
package com.adriandlph.auth.data.enumeration;

/**
 *
 * @author adriandlph
 *
 */
public enum EventType {
	USER_LOGOUT, // An user has been logued out --> add token to blacklist
	USER_BLOCKED, // Send the user id of an user who its not allowed to operate --> add user to blacklist
	USER_REMOVED, // (first you will recive a logout event to block all her tokens, but also, block its id)  --> remove all data of this user
	
	;	
	
}
