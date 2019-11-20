package com.example.demo.controller.student;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;
import com.example.demo.model.RequestList;;


@Controller
public class StudentHomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RequestService reqService;
	
	@GetMapping("student/home")
    public String get(Model model,Principal principal) {
		System.out.println(principal.getName());
		//List<RequestList> list = reqService.gerListOfStudent(principal.getName());
		//model.addAttribute("list",list);
		return "student/home";
	}
}
