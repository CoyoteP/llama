package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChatList {
	private String userId;
	
	private String userName;
	
	private Date sendDate;
	
	private String message;
}
