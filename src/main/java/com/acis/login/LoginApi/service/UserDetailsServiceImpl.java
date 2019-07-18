package com.acis.login.LoginApi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acis.login.LoginApi.dao.AppRoleDAO;
import com.acis.login.LoginApi.dao.AppUserDAO;
import com.acis.login.LoginApi.model.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired(required=true)
	AppRoleDAO appRoleDAO;
	
	public UserDetailsServiceImpl() {
		super();
		
	}

	public UserDetailsServiceImpl(AppRoleDAO appRoleDAO) {
		super();
		this.appRoleDAO = appRoleDAO;
	}


	@Autowired(required=true)
	AppUserDAO appUserDAO;
	public UserDetailsServiceImpl(AppUserDAO appUserDAO) {
		super();
		this.appUserDAO = appUserDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	AppUser appUser=this.appUserDAO.findUserAccount(username);
	
		if(appUser==null)
		{
			System.out.println("user not Found"+username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		else
		{
			System.out.println("user  Found"+username);
		}
		
		List<String> roleNames=this.appRoleDAO.getRoleNames(appUser.getUserId());
		
		
	    List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);
 
        return userDetails;
    }

	
}
