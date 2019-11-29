package com.example.demo.controller.student;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ChatList;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@Controller
public class StudentChatController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("student/chat")
    public String get(Model model,Principal principal) throws ParseException {
		List<ChatList> list = messageService.getListByStudent(principal.getName());
		model.addAttribute("chatList",list);
		System.out.println(list.size());
		return "student/chat";

	}
	@GetMapping("student/chat_message")
    public String message(Model model,Principal principal,@RequestParam("teacherId") String teacherId) {
		User teacher = userService.findByUserId(teacherId);
		User student = userService.findByUserId(principal.getName());
		List<Message> messages = messageService.findByStudentIdAndTeacherId(student.getUserId(),teacher.getUserId());
		model.addAttribute("student",student);
		model.addAttribute("teacher",teacher);
		model.addAttribute("messages",messages);
		System.out.println(messages.size());
		return "student/chat_message";
	}
}
