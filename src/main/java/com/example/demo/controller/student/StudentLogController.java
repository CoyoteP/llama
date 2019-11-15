package com.example.demo.controller.student;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class StudentLogController {
	@GetMapping("student/log")
    public String get(Model model,Principal principal) {
		return "log";
	}
}
