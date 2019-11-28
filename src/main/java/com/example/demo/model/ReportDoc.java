package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private Integer reportDocId;
	
	private String department;
	
	private String corporateName;
	
	private String knownMethod;
	
	private LocalDateTime eventDate;
	
	private String eventPlace;
	
	private Integer noticeDays;

	private String noticeTarget;

	private String noticeMethod;

	private Integer examTimes;

	private String examContent;

	private String interviewContent;
	
	private String memo;
	
	@OneToMany(mappedBy="reportDoc")
    private List<Request> requests;
	
	
}
