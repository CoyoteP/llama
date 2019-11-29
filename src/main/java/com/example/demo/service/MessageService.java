package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.ReportDocRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.ChatList;
import com.example.demo.model.Message;
import com.example.demo.model.ReportDoc;
import com.example.demo.model.ReportDocForm;
import com.example.demo.model.Request;
import com.example.demo.model.User;

@Service
@Transactional
public class MessageService {
	@Autowired
	MessageRepository messageRepo;

	

	public List<ChatList> getListByStudent(String studentId) throws ParseException {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String[][] objects = messageRepo.getListByStudent(studentId);
		List<ChatList> list = new ArrayList<ChatList>();
		for(int i = 0;i < objects.length;i++) {
			ChatList chat = new ChatList();
			chat.setUserId(objects[i][0]);
			chat.setUserName(objects[i][1]);
			chat.setSendDate(sdFormat.parse(objects[i][2]));
			chat.setMessage(objects[i][3]);
			list.add(chat);
		}
		System.out.println(objects);
		return list;
	}
	
	public List<ChatList> getListByTeacher(String teacherId) throws ParseException {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String[][] objects = messageRepo.getListByTeacher(teacherId);
		List<ChatList> list = new ArrayList<ChatList>();
		for(int i = 0;i < objects.length;i++) {
			ChatList chat = new ChatList();
			chat.setUserId(objects[i][0]);
			chat.setUserName(objects[i][1]);
			chat.setSendDate(sdFormat.parse(objects[i][2]));
			chat.setMessage(objects[i][3]);
			list.add(chat);
		}
		System.out.println(objects);
		return list;
	}



	public List<Message> findByStudentIdAndTeacherId(String studentId, String teacherId) {
		List<Message> messages = messageRepo.findByStudentUserIdAndTeacherUserId(studentId,teacherId);
		return messages;
	}



	public void save(Message message) {
		Message result = messageRepo.save(message);
		System.out.println("by:" + result.getSendBy());
		
	}


}
