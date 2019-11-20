package com.example.demo.controller.teacher;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.ReportDoc;
import com.example.demo.repository.ReportDocRepository;
import com.example.demo.service.UserService;

@Controller
public class TeacherReportLogController {
	@Autowired
	ReportDocRepository repoDocRepo;
	
	@GetMapping("teacher/report_log")
    public String get(Model model,Principal principal) {
		List<ReportDoc> report = repoDocRepo.getLogReports();
		model.addAttribute("reports",report);
		return "teacher/report_log";
	}
}
