package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;

@Controller
public class MessageController {
	@Autowired
    SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
    UserService userService;
	
	@Autowired
    MessageService messageService;
	
	@MessageMapping("/endpoint/{studentId}/{teacherId}") // エンドポイントの指定
    public void  greeting(@DestinationVariable String studentId,@DestinationVariable String teacherId,Message message,Principal principal) {
		System.out.println(studentId);
		System.out.println(principal.getName());
		User user = userService.findByUserId(principal.getName());
		message.setStudent(userService.findByUserId(studentId));
		message.setTeacher(userService.findByUserId(teacherId));
		message.setSendDate(new Date());
		if(user.getRole().equals("STUDENT")) {
			message.setSendBy("STUDENT");
			
		}else if(user.getRole().equals("TEACHER")) {
			message.setSendBy("TEACHER");
		}
		messageService.save(message);
		simpMessagingTemplate.convertAndSend("/topic/endpoint/" + studentId + "/" + teacherId, message.getMessage());
    }
}
