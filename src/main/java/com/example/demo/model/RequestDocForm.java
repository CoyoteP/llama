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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RequestDocForm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer requestDocId;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventStartDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventEndDate;

	@NotBlank
	private String eventPlace;

	@NotBlank
	@Pattern(regexp = "on")
	private String content;

	private String[] contents;
	
	@NotBlank
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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date absentStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date absentEndDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime delayDate;

	private String memo;

	@NotBlank
	private String classTeacherName;

	private String[] classTeacherNames;
	
	private String subjectTeacherName1;

	private String[] subjectTeacherNames;
	
	private String subjectTeacherName2;

	private String consent;
	
}
