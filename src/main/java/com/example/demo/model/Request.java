package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@OneToOne()
    @JoinColumn(name="studentId",nullable = true)
    private User student;
	
	@OneToOne()
    @JoinColumn(name="teacherId",nullable = true)
    private User teacher;
		
	@OneToOne()
    @JoinColumn(name="requestDocId",nullable = true)
    private RequestDoc requestDoc;
	
	@OneToOne()
    @JoinColumn(name="reportDocId",nullable = true)
    private ReportDoc reportDoc;
	
	private String docType;
	
	private String consent;

	

}
