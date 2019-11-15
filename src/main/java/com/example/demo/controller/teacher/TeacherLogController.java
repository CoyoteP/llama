package com.example.demo.controller.teacher;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TeacherLogController {
	@GetMapping("teacher/log")
    public String get(Model model,Principal principal) {
		return "log";
	}
}
