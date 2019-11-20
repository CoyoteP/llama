package com.example.demo.controller.teacher;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;



@Controller
public class TeacherStudentsSubmitController {
	@Autowired
	UserService userService;
	
	@GetMapping("teacher/signup_request")
    public String get(Model model,Principal principal) {
		System.out.println("aaaaaaaaaa");

		List<User> users = userService.getRequestStudents();
		System.out.println("aaaaaaaaaa" + users.size());
		model.addAttribute("users",users);
		return "teacher/signup_request";
	}
}
