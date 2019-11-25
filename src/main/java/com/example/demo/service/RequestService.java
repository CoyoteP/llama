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
		List<Request> list = reqRepo.findByStudentUserId(studentId);
		System.out.println(list.size());
		//List<RequestList> reqList = new ArrayList<RequestList>();
		for(Request request:list) {
			userRepo.findByUserId(studentId);
			RequestList req = new RequestList();
			if(Integer.parseInt(request.getDocType()) == 0) {
				RequestDoc reqDoc = reqDocRepo.findByRequestDocId(request.getRequestDoc().getRequestDocId());
				req.setRequestId(request.getRequestId() * 10);
				req.setConsent(request.getConsent());
				req.setDocId(reqDoc.getRequestDocId());
				req.setCorporateName(reqDoc.getCorporateOneName());
				req.setDocType(request.getDocType());
				req.setEventDate(reqDoc.getEventStartDate());
			}else{
				ReportDoc repoDoc = repoDocRepo.findByReportDocId(request.getReportDoc().getReportDocId());
				req.setRequestId(request.getRequestId());
				req.setDocType(request.getDocType());
				req.setConsent(request.getConsent());
				req.setDocId(repoDoc.getReportDocId());
				req.setCorporateName(repoDoc.getCorporateName());
				req.setEventDate(repoDoc.getEventDate());
			}
			//reqList.add(req);
		}
		//System.out.println(reqList.size());
		return list;
	}
	public List<RequestList> gerListOfTeacher(String studentId) {
		List<Request> list = reqRepo.findByTeacherUserId(studentId);
		return null;
	}
}
