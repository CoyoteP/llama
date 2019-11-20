package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "request")
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_id_seq")
    @SequenceGenerator(name = "request_id_seq", sequenceName = "request_id_seq", allocationSize = 1)
	@Column(nullable = false)
	private Integer requestId;
	
	private Date requestDate;
	
	private Date submitDate;
	
	private String studentId;
	
	private String teacherId;
	
	private Integer requestDocId;

	private Integer reportDocId;
	
	private String docType;
	
	private String consent;
	



}
