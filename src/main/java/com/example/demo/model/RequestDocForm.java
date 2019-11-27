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
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RequestDocForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer requestDocId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventEndDate;

	private String eventPlace;

	@Pattern(regexp = "on")
	private String content;

	private String[] contents;
	
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

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime leaveDate;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate absentStartDate;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate absentEndDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime delayDate;

	private String memo;

	private String classTeacherName;

	private String[] classTeacherNames;
	
	private String subjectTeacherName1;

	private String[] subjectTeacherNames;
	
	private String subjectTeacherName2;

	private String consent;
	
}
