package com.example.demo.controller.student;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.ReportDoc;
import com.example.demo.service.ReportDocService;

@Controller
public class StudentReportLogController {
	@Autowired
	ReportDocService repoDocService;
	
	@GetMapping("student/report_log")
    public String get(Model model,Principal principal) {
		List<ReportDoc> report = repoDocService.getLogs();
		model.addAttribute("reports",report);
		return "student/report_log";
	}
}
