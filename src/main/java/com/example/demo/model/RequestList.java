package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class RequestList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer requestId;
	
	private Integer docId;
	
	private LocalDateTime eventDate;
	
	private String corporateName;
	
	private String consent;
	
	private String docType;
	
	private Integer requestDocId;

	private Integer reportDocId;


	
	



}
