package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "reportDoc")
public class ReportDoc implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false)
	private String reportDocId;
	
	private String department;
	
	private String corporateName;
	
	private String kwnownMethod;
	
	private String eventDate;
	
	private String eventPlace;
	
	private String noticeDays;

	private String noticeTarget;

	private String noticeMethod;

	private String examTimes;

	private String examContent;

	private String interviewContent;
	
	private String memo;



}
