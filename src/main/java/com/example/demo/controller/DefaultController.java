package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class DefaultController {

	@GetMapping("default")
    public String get(Model model, HttpServletRequest request) {
		if(request.isUserInRole("ROLE_STUDENT")) {
	         return "";
	    }else if(request.isUserInRole("ROLE_TEACHER")){
	         return "";
	    }else if(request.isUserInRole("ROLE_ADMIN")){
	         return "";
	    }else {
	    	return null;
	    }
	}
}
