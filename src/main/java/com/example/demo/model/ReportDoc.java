package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "reportDoc")
public class ReportDoc implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_doc_id_seq")
    @SequenceGenerator(name = "report_doc_id_seq", sequenceName = "report_doc_id_seq", allocationSize = 1)
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
