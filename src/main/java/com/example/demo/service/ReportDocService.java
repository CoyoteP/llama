package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.ReportDocRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.ReportDoc;
import com.example.demo.model.ReportDocForm;
import com.example.demo.model.Request;
import com.example.demo.model.User;

@Service
@Transactional
public class ReportDocService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	ReportDocRepository repoDocRepo;
	
	@Autowired
	RequestRepository reqRepo;

	public boolean SaveOfStudent(User user) {
		// user.setRole("student");
		if (userRepo.save(user) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean save(ReportDoc reportDoc) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean request(ReportDocForm form, int reportId, String userId) {
		ReportDoc doc = new ReportDoc();
		User student = userRepo.findByUserId(userId) ;
		String department = student.getClassName().substring(0, 1);
		doc.setDepartment(department);
		doc.setCorporateName(form.getCorporateName());
		doc.setKnownMethod(form.getKnownMethod());
		doc.setEventDate(form.getEventDate());
		doc.setEventPlace(form.getEventPlace());
		doc.setNoticeDays(form.getNoticeDays().toString());
		doc.setNoticeTarget(form.getNoticeTarget());
		doc.setNoticeMethod(form.getNoticeMethod());
		doc.setExamTimes(form.getExamTimes().toString());
		doc.setExamContent(form.getExamContent());
		doc.setInterviewContent(form.getInterviewContent());
		doc.setMemo(form.getMemo());
		
		ReportDoc result = new ReportDoc();
		int submitType = 0;
		if (reportId == 0) {
			result = repoDocRepo.save(doc);
			submitType = 0;
		} else {
			doc.setReportDocId(reportId);
			result = repoDocRepo.save(doc);
			submitType = 1;

		}
		Request req = new Request();
		User teacher = userRepo.findByUserNameAndRoleAndEnable(form.getTeacherName(),"TEACHER","1");
		req.setRequestDate(new Date());
		//req.setStudentId(userId);
		req.setTeacher(teacher);
		req.setReportDoc(result);
		req.setDocType("1");
		if(submitType == 0) {
			req.setConsent("0");
		}else{
			req.setConsent("2");
		}
		
		reqRepo.save(req);
		return true;

	}
}
