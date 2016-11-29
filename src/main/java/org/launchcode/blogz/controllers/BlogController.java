package org.launchcode.blogz.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		List<User> allUsers = userDao.findAll();
		model.addAttribute("users", allUsers);
		return "index";
	}
	
	@RequestMapping(value = "/blog")
	public String blogIndex(Model model) {
		Iterable<Post> allPosts = postDao.findAll();
		model.addAttribute("posts", allPosts);
		return "blog";
	}
	
}
