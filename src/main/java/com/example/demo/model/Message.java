package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq", allocationSize = 1)
	private Integer messageId;
	
	@ManyToOne()
	@JoinColumn(name="studentId",nullable = true)
	private User student;
	
	@ManyToOne()
    @JoinColumn(name="teacherId",nullable = true)
    private User teacher;
	
	private String sendBy;
	
	private Date sendDate;
	
	private String message;
	
}
