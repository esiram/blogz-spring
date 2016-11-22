package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		// TODO - implement signup
		
		return "redirect:blog/newpost";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		
		//1) get parameters from request object
		//2) validate parameters (username, passwords, verify)--there are methods to do this in the user model class
		//3) if good, create a new user & store in a session
		//4) you can access the current session with the code: Session thisSession = request.getSession();
		//note: you will use the method in the abstract controller called SetUserInSession (see video ~ 21:52)
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		
		if(!password.equals(verify)){			    	//if passwords don't match.
			model.addAttribute("verify_error", "The passwords do not match.");
			model.addAttribute("username", username);
			return "signup";
		}
		else{
			if(!User.isValidUsername(username)){        //if invalid username
				model.addAttribute("username_error", "Invalid username.");
				model.addAttribute("username", username);
				return "signup";
			}
			else if(!User.isValidPassword(password)){	//if valid username but invalid password
				model.addAttribute("password_error", "Invalid password.");
				model.addAttribute("username", username);
				return "signup";
			}
			else{ //if everything qualifies and verifies   Note: Alyssa has this outside of the ELSE statement on hierarchical par with STRING in line 48
				User newUser =  new User(username, password);
				userDao.save(newUser);
				HttpSession thisSession = request.getSession();
				this.setUserInSession(thisSession, newUser);
				return "redirect:blog/newpost";
			}
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
//testing for github commit 11-21-16