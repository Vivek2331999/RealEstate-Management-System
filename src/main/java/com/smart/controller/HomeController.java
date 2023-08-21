package com.smart.controller;
//Real Estate
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class HomeController {
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home() {
		
		
		return "home";
	}
	@RequestMapping("/about")
	public String about() {
		
		return "about";
	}
	@RequestMapping("/search")
	public String search() {
		
		return "search";
	}
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	@RequestMapping("/contact")
	public String contact() {
		
		return "contact";
	}
	@RequestMapping("/listings")
	public String listings() {
		
		return "listings";
	}
	@RequestMapping("/view_property")
	public String view_property() {
		
		return "view_property";
	}
	
	@RequestMapping("/register")
	public String register(Model model) 
	{
		model.addAttribute("title", "Register - Real Estate Application");
		model.addAttribute("user",new User());
		return "register";
	}
	// handler for registering user
	@RequestMapping(value = "/do_register", method =RequestMethod.POST)
	public String registerUser(@ModelAttribute User user,HttpSession session)
	{
		

		boolean f = userService.checkEmail(user.getEmail());

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			User user1 = userService.createUser(user);
			if (user1 != null) {
				session.setAttribute("msg", "Signin Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "register";
	}

}
