package com.example.demo.controller.teacher;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherChatController {
	@GetMapping("teacher/chat")
    public String chat(Model model,Principal principal) {
		System.out.println("teacher/chat");
		return "teacher/chat";
	}
	
	@GetMapping("teacher/chat_message")
    public String message(Model model,Principal principal) {
		System.out.println("teacher/chat");
		return "teacher/chat_message";
	}
}
