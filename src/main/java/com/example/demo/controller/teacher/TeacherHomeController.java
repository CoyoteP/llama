package com.example.demo.controller.teacher;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherHomeController {
	
	@GetMapping("teacher")
    public String teacher(Model model,Principal principal) {
		System.out.println("Welcome home!");
		return "/teacher/home";
	}
	
	
	@GetMapping("/teacher/home")
    public String get(Model model,Principal principal) {
		System.out.println("Welcome home!");
		return "/teacher/home";
	}
}
