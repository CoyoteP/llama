package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepo;
	public boolean save(User user) {
		user.setEnable("0");
		if (userRepo.save(user) != null) {
			return true;
		} else {
			return false;
		}
	}
	public String getRole(String userId) {
		User user = userRepo.findByUserId(userId);
		return user.getRole();
	}
	public List<User> getRequestStudents() {
		List<User> users = userRepo.findByEnableAndRole("0","STUDENT");
		return users;
	}
	public List<User> getSubmitStudents() {
		List<User> users = userRepo.findByEnableAndRole("1","STUDENT");
		return users;
	}
	public boolean enable(String userId) {
		if (userRepo.enable(userId) == 1) {
			return true;
		} else {
			return false;
		}
	}
}
