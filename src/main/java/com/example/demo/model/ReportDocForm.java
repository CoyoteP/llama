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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportDocForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String corporateName;

	private String knownMethod;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime eventDate;

	private String eventPlace;

	@Min(0)
	private Integer noticeDays;

	private String noticeTarget;

	private String noticeMethod;

	@Min(1)
	@Max(9)
	private Integer examTimes;

	@Pattern(regexp = "on")
	private String examContent;

	private String[] examContents;

	@Pattern(regexp = "on")
	private String interviewContent;

	private String[] interviewContents;

	private String memo;

	private String teacherName;

	private String[] teacherNames;

}
