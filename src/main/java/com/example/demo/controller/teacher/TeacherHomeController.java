package com.example.demo.controller.teacher;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Request;
import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;

@Controller
public class TeacherHomeController {
	@Autowired
	UserService userService;
	
	@Autowired
	RequestService reqService;

	
	@GetMapping("/teacher/home")
    public String get(Model model,Principal principal) {
		List<Request> list = reqService.getListOfTeacher(principal.getName());
		int[] count = reqService.count(list);
		model.addAttribute("count",count);
		model.addAttribute("list",list);
		return "/teacher/home";
	}
	
	@PostMapping(value = "/teacher/home",params = "cancel")
	public String cancel(RedirectAttributes attributes, Model model,Principal principal,@RequestParam("requestId") Integer requestId,@RequestParam("consent") String consent) {
		String result = reqService.cancel(requestId,consent);
		attributes.addFlashAttribute("result",result);
		return "redirect:/teacher/home";
	}
	
	@PostMapping(value = "/teacher/home",params = "submit")
	public String submit(RedirectAttributes attributes, Model model,Principal principal,@RequestParam("requestId") Integer requestId,@RequestParam("consent") String consent) {
		String result = reqService.submit(requestId,consent);
		attributes.addFlashAttribute("result",result);

		return "redirect:/teacher/home";
	}
}
