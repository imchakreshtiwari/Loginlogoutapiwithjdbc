package com.acis.login.LoginApi.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acis.login.LoginApi.utils.WebUtils;

@Controller
public class MainController {

	@RequestMapping(value={"/" ,"/welcome"},method=RequestMethod.GET)
	public String welcomePage(Model model)
	{
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is a welcome page");
		return "welcomepage";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String adminPage(Model model,Principal principal)
	{
		User loginuser = (User) ((Authentication) principal).getPrincipal();
		String userinfo=WebUtils.toString(loginuser);
		model.addAttribute("Userinfo", userinfo);
		return "adminPage";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model)
	{
		return "loginPage";
	}
	
	@RequestMapping(value="/logoutsuccessful",method=RequestMethod.GET)
	public String logout(Model model)
	{
		 model.addAttribute("title", "Logout");
		return "logoutsuccessfulPage";
	}
	
	@RequestMapping(value="/userinfo",method=RequestMethod.GET)
	public String userinfo(Model model,Principal principal)
	{
		String userName=principal.getName();
		
		System.out.println("userName"+userName);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		
		return "userinfoPage";
	
	}
	
	@RequestMapping(value="/403",method=RequestMethod.GET)
	 public String accessDenied(Model model, Principal principal) {
		 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
	
	
}
