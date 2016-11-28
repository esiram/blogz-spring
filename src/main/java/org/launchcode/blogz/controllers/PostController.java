package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {
		
		// TODO - implement newPost
		String body = request.getParameter("body");
//		date created = request.getParameter("created");
//	    date modified = request.getParameter("modified");
		String title = request.getParameter("title");
		User author = request.getParameter(user-id);//how do I get the author here?
        
		if(title == "" || title == null){
			model.addAttribute("error", "Title required.");
			return "newpost";
		}
		else if(body == "" || body == null){
			model.addAttribute("error", "Content required.");
			model.addAttribute("title", title);
			return "newpost";
		}
		else{//if title and body != null & != ""
			Post newPost = new Post(title, body, author);
			postDao.save(newPost);
			//do I need to put something in a session here?
			return "redirect:blog/post";		
		}
	
		return "redirect:index"; // TODO - this redirect should go to the new post's page  		
	}
	
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {
		
		// TODO - implement singlePost
		
		return "post";
	}
	
	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable String username, Model model) {
		
		// TODO - implement userPosts
		
		return "blog";
	}
	
}



