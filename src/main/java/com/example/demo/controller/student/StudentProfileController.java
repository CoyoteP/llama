package com.example.demo.controller.student;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentProfileController {
	@GetMapping("student/profile")
    public String get(Model model,Principal principal) {
		return "student/profile";
	}
	
	@PostMapping("student/profile")
    public String post(Model model,Principal principal) {
		return "student/profile";
	}
}
