package com.example.demo.controller.student;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;
import com.example.demo.model.Request;
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
		List<Request> list = reqService.getListOfStudent(principal.getName());
		System.out.println("size:" + list.size());
		for(Request request:list) {
			System.out.println("DocType:" + request.getDocType());
			if(request.getDocType().equals("0")) {
				System.out.println(request.getRequestDoc().getCorporateOneName());
			}else if(request.getDocType().equals("1")) {
				System.out.println(request.getReportDoc().getCorporateName());
			}
		}
		model.addAttribute("list",list);
		return "student/home";
	}
	@GetMapping("student/home2")
    public String get2(Model model,Principal principal) {
		System.out.println(principal.getName());
		//List<RequestList> list = reqService.gerListOfStudent(principal.getName());
		//model.addAttribute("list",list);
		return "student/home2";
	}
	@GetMapping("student/home3")
    public String get3(Model model,Principal principal) {
		System.out.println(principal.getName());
		//List<RequestList> list = reqService.gerListOfStudent(principal.getName());
		//model.addAttribute("list",list);
		return "student/home3";
	}
}
