package com.example.demo.controller;

import java.security.Principal;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UserService;

@Controller
public class DefaultController {
	@Autowired
	UserService userService;

	@GetMapping("default")
	public String get(Model model, Principal principal) {
		String role = userService.getRole(principal.getName());
		if (role.equals("STUDENT")) {
			return "redirect:/student/home";
		} else if (role.equals("TEACHER")) {
			return "redirect:/teacher/home";
		} else if (role.equals("ADMIN")) {
			return "redirect:/admin/home";
		} else {
			return "ques";
		}
	}
}
