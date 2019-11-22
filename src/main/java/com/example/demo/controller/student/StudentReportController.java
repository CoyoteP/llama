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

import com.example.demo.model.ReportDoc;
import com.example.demo.model.ReportDocForm;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReportDocService;
import com.example.demo.service.UserService;

@Controller
@SessionAttributes(value="reportDocForm")
public class StudentReportController {
	
	@Autowired
	ReportDocService repoDocService;

	@Autowired
	UserService userService;

	final static Map<String, String> KNWON_METHOD_ITEMS = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("学校斡旋", "学校斡旋");
					put("斡旋サイト", "斡旋サイト");
					put("斡旋サイト", "斡旋サイト");
					put("新聞・雑誌", "新聞・雑誌");
					put("ジョブカフェ等", "ジョブカフェ等");
					put("その他（メモに記述）", "その他");
				}
			});

	final static Map<String, String> NOTICE_TARGET_ITEMS = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("本人", "本人");
					put("学校", "学校");
				}
			});

	final static Map<String, String> NOTICE_METHOD_ITEMS = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("電話", "電話");
					put("郵送", "郵送");
					put("メール", "メール");
					put("Web", "Web");
				}
			});

	final static Map<String, String> EXAM_CONTENT_ITEMS = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("SPI", "SPI");
					put("CAB/GAB", "CAB/GAB");
					put("職業適性", "職業適性");
					put("クレペリン", "クレペリン");
					put("一般常識", "一般常識");
					put("専門知識", "専門知識");
					put("作文", "作文");
					put("その他（メモに記述）", "その他");

				}
			});

	final static Map<String, String> INTERVIEW_CONTENT_ITEMS = Collections
			.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("個人面接", "個人面接");
					put("集団面接", "集団面接");
					put("グループディスカッション", "グループディスカッション");
					put("グループワーク", "グループワーク");
				}
			});

	@GetMapping("student/report")
	public String get(Model model, ReportDocForm reportDocForm, Principal principal) {
		Map<String, String> TEACHER_NAMES_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
			{
				List<String> teacherNames = userService.getAllTeacherName();
				for (String teacherName : teacherNames) {
					put(teacherName, teacherName);
				}
			}
		});
		model.addAttribute("knownMethods", KNWON_METHOD_ITEMS);
		model.addAttribute("noticeTargets", NOTICE_TARGET_ITEMS);
		model.addAttribute("noticeMethods", NOTICE_METHOD_ITEMS);
		model.addAttribute("examContents", EXAM_CONTENT_ITEMS);
		model.addAttribute("interviewContents", INTERVIEW_CONTENT_ITEMS);
		model.addAttribute("teacherNames", TEACHER_NAMES_ITEMS);
		reportDocForm.setTeacherName(userService.getClassTeacher(principal.getName()));

		return "student/report";
	}

	@GetMapping("student/report_check")
	public String home(Model model, @ModelAttribute ReportDocForm reportDocForm) {
		return "student/home";
	}

	@PostMapping("student/report_check")
	public String check(@Validated @ModelAttribute ReportDocForm reportDocForm, BindingResult result, Model model) {
		// if (result.hasErrors()) {
		// model.addAttribute("validationError", "不正な値が入力されました");
		// return "student/report";
		// }		
		ReportDocForm form = reportDocForm;
		setRequestForm(reportDocForm);
		System.out.println(form.getExamContent());
		return "student/report_check";
	}

	@PostMapping("student/report")
	public String post(Model model,Principal principal, @ModelAttribute("reportDocForm")ReportDocForm form,SessionStatus status) {
		repoDocService.request(form,0,principal.getName());
		status.setComplete();
		return "student/home3";
	}
	@ModelAttribute("reportDocForm")
    public ReportDocForm setRequestForm(ReportDocForm reportDocForm){
        return reportDocForm;
    }
}
