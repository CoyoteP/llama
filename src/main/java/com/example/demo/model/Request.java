package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "request")
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false)
	private String requestId;
	
	private String requestDate;
	
	private String reportDate;
	
	private String studentId;
	
	private String teacherId;
	
	private String requestDocId;

	private String reportDocId;
	
	private String consent;


}
