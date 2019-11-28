package com.example.demo.controller.teacher;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;



@Controller
public class TeacherStudentsSubmitController {
	@Autowired
	UserService userService;
	
	@GetMapping("teacher/signup_request")
    public String get(Model model,Principal principal) {

		List<User> users = userService.getRequestStudents();
		model.addAttribute("users",users);
		return "teacher/students_signup_manager";
	}
	
	@PostMapping("teacher/signup_request")
    public String post(Model model,Principal principal,@RequestParam(name = "studentId") String studentId) {
		
		if (userService.enable(studentId)) {
			model.addAttribute("success", "ユーザ登録要求を行いました。");
			System.out.println("success");
		} else {
			model.addAttribute("error", "ユーザ登録に失敗しました。");
			System.out.println("error");
		}
		return "redirect:/teacher/signup_request";
	}
}
