package com.acis.login.LoginApi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

	public static String passwordencoder(String password)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);	
	}
	 public static void main(String[] args) {
	        String password = "123";
	        String encrytedPassword = passwordencoder(password);
	 
	        System.out.println("Encryted Password: " + encrytedPassword);
	    }
}
