package com.example.demo.controller.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class AdminTeachersSubmitController {
	@Autowired
	UserService userService;
	
	@GetMapping("admin/teachers_signup_manager")
    public String get(Model model,Principal principal) {
		List<User> users = userService.getRequestTeachers();
		model.addAttribute("users",users);
		return "admin/teachers_signup_manager";
	}
}
