package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class SignupController {
	@Autowired
	UserService userService;

	@GetMapping("signup")
	public String get(User user, Model model) {
		return "signup";
	}

	@PostMapping("signup")
	public String post(Model model,@ModelAttribute User user) {
		if(user.getRole() == null) {
			user.setRole("STUDENT");
		}
		if (userService.save(user)) {
			model.addAttribute("success", "ユーザ登録要求の承認を行いました。");
			System.out.println("success");
		} else {
			model.addAttribute("error", "ユーザ登録の承認に失敗しました。");
			System.out.println("error");
		}
		return "login";
	}
}
