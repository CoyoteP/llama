package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	public boolean request(ReportDocForm form, String userId) {
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
		if (form.getConsent() == null) {
			repoDocRepo.save(doc);
		} else {
			doc.setReportDocId(form.getReportDocID());
			repoDocRepo.save(doc);
		}
		Request req = new Request();
		User teacher = userRepo.findByUserNameAndRoleAndEnable(form.getTeacherName(),"TEACHER","1");
		req.setRequestDate(new Date());
		req.setStudent(student);
		req.setTeacher(teacher);
		req.setReportDoc(doc);
		req.setDocType("1");
		if(form.getConsent() == null) {
			req.setConsent("0");
			reqRepo.save(req);
		}else if(form.getConsent().equals("1")){
			System.out.println("request:1");
			reqRepo.editReport(req.getReportDoc().getReportDocId(),teacher.getUserId(),"0");
		}else if(form.getConsent().equals("2") || form.getConsent().equals("4")) {
			System.out.println("request:2or4");
			reqRepo.editReport(req.getReportDoc().getReportDocId(),teacher.getUserId(),"3");
		}else {
			System.out.println("request:other");
		}
		return true;

	}

	public ReportDoc findByReporttDocId(Integer reportDocId) {
		ReportDoc reportDoc = repoDocRepo.findByReportDocId(reportDocId);
		return reportDoc;
	}

	public ReportDocForm importByDoc(ReportDoc doc, String consent) {
		ReportDocForm form = new ReportDocForm();
		form.setReportDocID(doc.getReportDocId());
		form.setCorporateName(doc.getCorporateName());
		form.setKnownMethod(doc.getKnownMethod());
		form.setEventDate(doc.getEventDate());
		form.setEventPlace(doc.getEventPlace());
		form.setNoticeDays(Integer.parseInt(doc.getNoticeDays()));
		form.setNoticeTarget(doc.getNoticeTarget());
		form.setNoticeMethod(doc.getNoticeTarget());
		form.setExamTimes(Integer.parseInt(doc.getExamTimes()));
		form.setExamContent(doc.getExamContent());
		form.setInterviewContent(doc.getInterviewContent());
		form.setMemo(doc.getMemo());
		List<String> teachers = reqRepo.getTeacherNamesOfReportDocId(doc.getReportDocId());
		form.setTeacherName(teachers.get(0));
		form.setConsent(consent);

		return form;
	}

	public List<ReportDoc> getLogs() {
		List<Integer> requests = reqRepo.getLogReportDocIds();
		System.out.println("ookisa:" + requests);
		List<ReportDoc> list = repoDocRepo.getLogReports(requests);
		return list;
	}

}
