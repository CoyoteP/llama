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
public class TeacherStudentsManagerController {
	@Autowired
	UserService userService;
	
	@GetMapping("teacher/students_manager")
    public String get(Model model,Principal principal) {
		List<User> users = userService.getSubmitStudents();
		model.addAttribute("users",users);
		return "teacher/students_manager";
	}
}
