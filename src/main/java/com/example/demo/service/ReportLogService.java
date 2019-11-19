package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;

@Service
@Transactional
public class ReportLogService {
	@Autowired
	UserRepository userRepo;
	
	public boolean SaveOfStudent(User user) {
		//user.setRole("student");
		if(userRepo.save(user) != null) {
			return true;
		}else{
			return false;
		}
	}
}
