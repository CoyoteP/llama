package com.example.demo.controller.teacher;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherProfileController {
	@GetMapping("teacher/profile")
    public String get(Model model,Principal principal) {
		return "teacher/profile";
	}
	
	@PostMapping("teacher/profile")
    public String post(Model model,Principal principal) {
		return "teacher/chat_message";
	}
}
