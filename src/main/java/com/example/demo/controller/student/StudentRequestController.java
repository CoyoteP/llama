package com.example.demo.controller.student;

import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String get(Model model, RequestDocForm requestDocForm,Principal principal,@ModelAttribute("model")ModelMap modelMap) {
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
		requestDocForm = (RequestDocForm) modelMap.get("requestDocForm");
		if(requestDocForm == null) {
			requestDocForm = new RequestDocForm();
			requestDocForm.setClassTeacherName(userService.getClassTeacher(principal.getName()));
		}
		model.addAttribute("requestDocForm",requestDocForm);
		model.addAttribute("contents",CONTENTS_ITEMS);
		model.addAttribute("classTeacherNames",CLASS_TEACHER_NAMES_ITEMS);
		model.addAttribute("subjectTeacherNames",SUBJECT_TEACHER_NAMES_ITEMS);
		
		return "student/request";
	}
	@PostMapping("student/request_check")
	public String check(@Validated @ModelAttribute RequestDocForm requestDocForm, BindingResult result, Model model) {
		// if (result.hasErrors()) {
		// model.addAttribute("validationError", "不正な値が入力されました");
		// return "student/report";
		// }	
		System.out.println("OK:" + requestDocForm.getAbsentStartDate());
		setRequestForm(requestDocForm);
		return "student/request_check";
	}

	@PostMapping("student/request")
	public String post(Model model,Principal principal, @ModelAttribute("requestDocForm")RequestDocForm form,SessionStatus status) {
		reqDocService.request(form,principal.getName());
		status.setComplete();
		return "redirect:/student/home";
	}
	
	@PostMapping("student/request/edit")
	public String edit(Model model,Principal principal, RedirectAttributes redirectAttributes,@ModelAttribute RequestDocForm requestDocForm ,@ModelAttribute("requestDocId") Integer requestDocId,@ModelAttribute("consent") String consent) {
		RequestDoc requestDoc = reqDocService.findByRequestDocId(requestDocId);
		requestDocForm = reqDocService.importByDoc(requestDoc,consent);
		ModelMap modelMap = new ModelMap();
	    modelMap.addAttribute("requestDocForm", requestDocForm);

		redirectAttributes.addFlashAttribute("model", modelMap);
		setRequestForm(requestDocForm);
		return "redirect:/student/request";
	}
	@ModelAttribute("requestDocForm")
    public RequestDocForm setRequestForm(RequestDocForm requestDocForm){
        return requestDocForm;
    }
}
