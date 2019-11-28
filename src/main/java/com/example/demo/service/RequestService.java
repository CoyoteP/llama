package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.ReportDocRepository;
import com.example.demo.repository.RequestDocRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.ReportDoc;
import com.example.demo.model.Request;
import com.example.demo.model.RequestDoc;
import com.example.demo.model.RequestList;
import com.example.demo.model.User;

@Service
@Transactional
public class RequestService {
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ReportDocRepository repoDocRepo;
	
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
	public List<Request> getListOfStudent(String studentId) {
		List<Request> list = reqRepo.findByStudentUserIdOrderByRequestIdDesc(studentId);
		return list;
	}
	public List<Request> getListOfTeacher(String teacherId) {
		List<Request> list = reqRepo.findByTeacherUserIdOrderByRequestIdDesc(teacherId);
		System.out.println(list.size());
		return list;
	}
	public String cancel(Integer requestId, String consent) {
		if(consent.equals("0")) {
			consent = "1";
		}else if(consent.equals("3")) {
			consent = "4";
		}else {return "miss";}
		int status = reqRepo.updateConsent(requestId, consent);
		if(status == 1) {
			return "完了";
		}else {
			return "miss:sql";
		}		
	}
	public String submit(Integer requestId, String consent) {
		if(consent.equals("0")) {
			consent = "2";
		}else if(consent.equals("3")) {
			consent = "5";
		}else {return "miss";}
		int status = reqRepo.updateConsent(requestId, consent);
		if(status == 1) {
			return "完了";
		}else {
			return "miss:sql";
		}		
	}
}
