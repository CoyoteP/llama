package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class SignupController {
	@Autowired
	UserService userService;

	@GetMapping("signup")
	public String get(Model model) {
		return "signup";
	}

	@PostMapping("signup")
	public String post(Model model) {
		User user = null;
		if (userService.SaveOfStudent(user)) {
			model.addAttribute("success", "ユーザ登録要求を行いました。");
		} else {
			model.addAttribute("error", "ユーザ登録に失敗しました。");
		}
		return "login";

	}
}
