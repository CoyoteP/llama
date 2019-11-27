package com.example.demo.service;

import java.util.Date;

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

	public boolean request(RequestDocForm form, int requestId, String userId) {
		RequestDoc doc = new RequestDoc();
		User student = userRepo.findByUserId(userId) ;
		String department = student.getClassName().substring(0, 1);
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
		RequestDoc result = new RequestDoc();
		int submitType = 0;
		if (requestId == 0) {
			result = reqDocRepo.save(doc);
			submitType = 0;
		} else {
			doc.setRequestDocId(requestId);
			result = reqDocRepo.save(doc);
			submitType = 1;

		}
		Request req = new Request();
		User teacher = userRepo.findByUserNameAndRoleAndEnable(form.getClassTeacherName(),"TEACHER","1");
		req.setRequestDate(new Date());
		//req.setStudentId(userId);
		req.setTeacher(teacher);
		req.setRequestDoc(result);
		req.setDocType("0");
		req.setConsent("0");
		if(submitType == 0) {
			req.setConsent("0");
		}else{
			req.setConsent("2");
		}
		
		reqRepo.save(req);
		return true;

	}

	public RequestDoc findByRequestDocId(Integer requestDocId) {
		RequestDoc requestDoc = reqDocRepo.findByRequestDocId(requestDocId);
		return requestDoc;
	}

	public RequestDocForm importByDoc(RequestDoc doc, String consent) {
		RequestDocForm form = new RequestDocForm();
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
		
		
		return form;
	}
}
