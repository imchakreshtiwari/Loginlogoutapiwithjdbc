package com.acis.login.LoginApi.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class WebUtils {

	
	public static String toString(User user)
	{
		StringBuffer sb=new StringBuffer();
		sb.append("UserName:").append(user.getUsername());
		Collection<GrantedAuthority> authorities=user.getAuthorities();
		 if (authorities != null && !authorities.isEmpty()) {
	            sb.append(" (");
	            boolean first = true;
	            for (GrantedAuthority a : authorities) {
	                if (first) {
	                    sb.append(a.getAuthority());
	                    first = false;
	                } else {
	                    sb.append(", ").append(a.getAuthority());
	                }
	            }
	            sb.append(")");
	        }
	        return sb.toString();
	    }
}
