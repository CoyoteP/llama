package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.RequestDocRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.ReportDoc;
import com.example.demo.model.ReportDocForm;
import com.example.demo.model.Request;
import com.example.demo.model.RequestDoc;
import com.example.demo.model.RequestDocForm;
import com.example.demo.model.User;

@Service
@Transactional
public class RequestDocService {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RequestDocRepository reqDocRepo;
	
	@Autowired
	RequestRepository reqRepo;
	
	public boolean SaveOfStudent(User user) {
		//user.setRole("student");
		if(userRepo.save(user) != null) {
			return true;
		}else{
			return false;
		}
	}

	public boolean request(RequestDocForm form,  String userId) {
		RequestDoc doc = new RequestDoc();
		User student = userRepo.findByUserId(userId) ;
		String department = student.getClassName().substring(0, 1);
		//Integer id = form.getRequestDocId();
		//if(id != 0) {
		//	doc.setRequestDocId(id);
		//}
		
		doc.setEventStartDate(form.getEventStartDate());
		doc.setEventEndDate(form.getEventEndDate());
		doc.setEventPlace(form.getEventPlace());
		doc.setContent(form.getContent());
		doc.setCorporateOneName(form.getCorporateOneName());
		doc.setCorporateTwoName(form.getCorporateTwoName());
		doc.setCorporateThreeName(form.getCorporateThreeName());
		doc.setCorporateFourName(form.getCorporateFourName());
		doc.setCorporateFiveName(form.getCorporateFiveName());
		doc.setLeaveDate(form.getLeaveDate());
		doc.setAbsentStartDate(form.getAbsentStartDate());
		doc.setAbsentEndDate(form.getAbsentEndDate());
		doc.setDelayDate(form.getDelayDate());
		doc.setMemo(form.getMemo());
		if (form.getConsent() == null) {
			reqDocRepo.save(doc);
		} else {
			doc.setRequestDocId(form.getRequestDocId());
			reqDocRepo.save(doc);
		}
		Request req = new Request();
		User teacher = userRepo.findByUserNameAndRoleAndEnable(form.getClassTeacherName(),"TEACHER","1");
		User subTeacher1 = new User();
		User subTeacher2 = new User();
		if(!(form.getSubjectTeacherName1() == null)) {
			subTeacher1 = userRepo.findByUserNameAndRoleAndEnable(form.getSubjectTeacherName1(),"TEACHER","1");
		}
		if(!(form.getSubjectTeacherName2() == null)) {
			subTeacher2 = userRepo.findByUserNameAndRoleAndEnable(form.getSubjectTeacherName2(),"TEACHER","1");
		}
		
		req.setRequestDate(new Date());
		req.setStudent(student);
		req.setTeacher(teacher);
		req.setRequestDoc(doc);
		req.setDocType("0");
		req.setConsent("0");
		if(form.getConsent() == null) {
			System.out.println("request:0");
			req.setConsent("0");
			reqRepo.save(req);
		}else if(form.getConsent().equals("1")){
			System.out.println("request:1");
			reqRepo.editRequest(req.getRequestDoc().getRequestDocId(),teacher.getUserId(),"0");
			if(subTeacher1.getUserId() != null) {
				reqRepo.editRequest(req.getRequestDoc().getRequestDocId(),subTeacher1.getUserId(),"0");
			}
			if(subTeacher2.getUserId() != null) {
				reqRepo.editRequest(req.getRequestDoc().getRequestDocId(),subTeacher2.getUserId(),"0");
			}
		}else if(form.getConsent().equals("2") || form.getConsent().equals("4")) {
			System.out.println("request:2or4 ->" + req.getRequestDoc().getRequestDocId() + ":" + teacher.getUserId());
			reqRepo.editRequest(req.getRequestDoc().getRequestDocId(),teacher.getUserId(),"3");
		}else {
			System.out.println("request:other");

		}
		
		return true;

	}

	public RequestDoc findByRequestDocId(Integer requestDocId) {
		RequestDoc requestDoc = reqDocRepo.findByRequestDocId(requestDocId);
		return requestDoc;
	}

	public RequestDocForm importByDoc(RequestDoc doc, String consent) {
		RequestDocForm form = new RequestDocForm();
		form.setRequestDocId(doc.getRequestDocId());
		form.setEventStartDate(doc.getEventStartDate());
		form.setEventEndDate(doc.getEventEndDate());
		form.setEventPlace(doc.getEventPlace());
		form.setContent(doc.getContent());
		form.setCorporateOneName(doc.getCorporateOneName());
		form.setCorporateOneProgression(doc.getCorporateOneProgression());
		form.setCorporateTwoName(doc.getCorporateTwoName());
		form.setCorporateTwoProgression(doc.getCorporateTwoProgression());
		form.setCorporateThreeName(doc.getCorporateThreeName());
		form.setCorporateThreeProgression(doc.getCorporateThreeProgression());
		form.setCorporateFourName(doc.getCorporateFourName());
		form.setCorporateFourProgression(doc.getCorporateFourProgression());
		form.setCorporateFiveName(doc.getCorporateFiveName());
		form.setCorporateFiveProgression(doc.getCorporateFiveProgression());
		form.setLeaveDate(doc.getLeaveDate());
		form.setAbsentStartDate(doc.getAbsentStartDate());
		form.setAbsentEndDate(doc.getAbsentEndDate());
		form.setDelayDate(doc.getDelayDate());
		form.setMemo(doc.getMemo());
		form.setConsent(consent);
		List<String> teachers = reqRepo.getTeacherNamesOfRequestDocId(doc.getRequestDocId());
		
		form.setClassTeacherName(teachers.get(0));
		if(teachers.size() >= 2) {
			form.setSubjectTeacherName1(teachers.get(1));
		}
		if(teachers.size() >= 3) {
			form.setSubjectTeacherName2(teachers.get(2));
		}
		return form;
	}
}
