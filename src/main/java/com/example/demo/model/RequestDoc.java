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
@Table(name = "requestDoc")
public class RequestDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_doc_id_seq")
    @SequenceGenerator(name = "request_doc_id_seq", sequenceName = "request_doc_id_seq", allocationSize = 1)
	@Column(nullable = false)
	private String requestDocId;

	private String eventStartDate;

	private String eventEndDate;

	private String eventPlace;

	private String content;

	private String corporateOneName;

	private String corporateOneProgression;

	private String corporateOTwoName;

	private String corporateTwoProgression;
	
	private String corporateThreeName;

	private String corporateThreeProgression;
	
	private String corporateFourName;

	private String corporateFourProgression;
	
	private String corporateFiveName;

	private String corporateFiveProgression;

	private String leaveDate;

	private String absentStartDate;

	private String absentEndDate;

	private String delayDate;

	private String memo;


}
