package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private Integer requestDocId;

	private LocalDateTime eventStartDate;

	private LocalDateTime eventEndDate;

	private String eventPlace;

	private String content;

	private String corporateOneName;

	private String corporateOneProgression;

	private String corporateTwoName;

	private String corporateTwoProgression;
	
	private String corporateThreeName;

	private String corporateThreeProgression;
	
	private String corporateFourName;

	private String corporateFourProgression;
	
	private String corporateFiveName;

	private String corporateFiveProgression;

	private LocalDateTime leaveDate;

	private LocalDate absentStartDate;

	private LocalDate absentEndDate;

	private LocalDateTime delayDate;

	private String memo;


}
