package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable = false)
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String className;
	
	private String classNumber;
	
	private String watchWord;
	
	private String enable;
	
	private String role;
	
	@OneToMany(mappedBy="student")
    private List<Request> requestsOfStudent;
	
	@OneToMany(mappedBy="teacher")
    private List<Request> requestsOfTeacher;
	

}
