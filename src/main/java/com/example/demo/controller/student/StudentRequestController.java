package com.example.demo.controller.student;

import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.model.RequestDoc;
import com.example.demo.model.RequestDocForm;
import com.example.demo.service.RequestDocService;
import com.example.demo.service.UserService;

@Controller
@SessionAttributes(value="requestDocForm")
public class StudentRequestController {
	
	@Autowired
	RequestDocService reqDocService;

	@Autowired
	UserService userService;
	
	final static Map<String, String> CONTENTS_ITEMS = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("面接", "面接");
					put("試験", "試験");
					put("説明会", "説明");
					put("その他", "その他");
				}
			});

	@GetMapping("student/request")
	public String get(@ModelAttribute("requestDoc") RequestDoc requestDoc,Model model, RequestDocForm requestDocForm,Principal principal) {
		Map<String, String> CLASS_TEACHER_NAMES_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
			{
				List<String> teacherNames = userService.getAllTeacherName();
				for (String teacherName : teacherNames) {
					put(teacherName, teacherName);
				}
			}
		});
		Map<String, String> SUBJECT_TEACHER_NAMES_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
			{
				List<String> teacherNames = userService.getAllTeacherName();
				for (String teacherName : teacherNames) {
					put(teacherName, teacherName);
				}
			}
		});
		model.addAttribute("contents",CONTENTS_ITEMS);
		model.addAttribute("classTeacherNames",CLASS_TEACHER_NAMES_ITEMS);
		model.addAttribute("subjectTeacherNames",SUBJECT_TEACHER_NAMES_ITEMS);
		requestDocForm.setClassTeacherName(userService.getClassTeacher(principal.getName()));
		return "student/request";
	}
	@PostMapping("student/request_check")
	public String check(@Validated @ModelAttribute RequestDocForm requestDocForm, BindingResult result, Model model) {
		// if (result.hasErrors()) {
		// model.addAttribute("validationError", "不正な値が入力されました");
		// return "student/report";
		// }	
		setRequestForm(requestDocForm);
		return "student/request_check";
	}

	@PostMapping("student/request")
	public String post(Model model,Principal principal, @ModelAttribute("requestDocForm")RequestDocForm form,SessionStatus status) {
		reqDocService.request(form,0,principal.getName());
		status.setComplete();
		return "student/home";
	}
	
	@PostMapping("student/request/edit")
	public String edit(Model model,Principal principal, @ModelAttribute("requestDocId") Integer requestDocId,@ModelAttribute("consent") String consent) {
		RequestDoc requestDoc = reqDocService.findByRequestDocId(requestDocId);
		RequestDocForm requestDocForm = reqDocService.importByDoc(requestDoc,consent);
		setRequestForm(requestDocForm);
		return "redirect:student/request";
	}
	@ModelAttribute("requestDocForm")
    public RequestDocForm setRequestForm(RequestDocForm requestDocForm){
        return requestDocForm;
    }
}
