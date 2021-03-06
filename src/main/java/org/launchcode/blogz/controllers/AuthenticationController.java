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
	public String signup(HttpServletRequest request, Model model) { //Model and model.AddAttribute() on intro to Thymeleaf video circa 14:30
		
		// TODO - implement signup
		
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
				model.addAttribute("username_error", "Invalid username."); //model.addAttribute puts info into templates (see intro to Thymleaf video circa 15:00)
				model.addAttribute("username", username);
				return "signup";
			}
			else if(!User.isValidPassword(password)){	//if valid username but invalid password
				model.addAttribute("password_error", "Invalid password.");
				model.addAttribute("username", username);
				return "signup";
			}
			else{ //if everything qualifies and verifies   
				User newUser =  new User(username, password);
				userDao.save(newUser);
				HttpSession thisSession = request.getSession();
				this.setUserInSession(thisSession, newUser);
				return "redirect:blog/newpost";
			}
		}
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		
		// TODO - implement login
		
		//1) get parameters from request object
		//2) get user from username
		//3) check that password is correct //HAVING ISSUES WITH LOGIN as of 11-28-16
		//4) log user in; if so you log the user in by setting the user in the session; similar to the signup TODO
		//Note: you might consider making a helper method to to call for the the signup and login TODs (see video ~ 23:06)
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.findByUsername(username); //I think this method needs to get made in the UserDao // YES :)
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		//boolean checkUser = username.equals(user.getUsername()); 
		boolean checkUser = username.equals(user);
		
		if(checkUser != true){
			model.addAttribute("error", "Please enter a valid username and a valid password.");
			return "login";
		}
//		if(checkUser != true && user.isMatchingPassword(password) != true){
//			model.addAttribute("error", "Please enter a valid username and a valid password.");
//			return "login";
//		}
		else if(user.isMatchingPassword(password) != true){
			model.addAttribute("error", "Please enter a valid password.");
			model.addAttribute("username", username);
			return "login";
		}
//		else if(user.isMatchingPassword(password) != true && checkUser != true){
//			model.addAttribute("error", "Please enter a valid password and a valid username; or signup.");
//			return "login";
//		}
		else{//if everything works
			HttpSession thisSession = request.getSession();
			this.setUserInSession(thisSession, user);
			return "redirect:blog/newpost";
			}
		}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
