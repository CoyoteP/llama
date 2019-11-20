package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Request;
import com.example.demo.model.RequestList;
import com.example.demo.model.User;

@Transactional
@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
	
	List<Request> findByStudentId(@Param("studentId") String studentId);
	
	List<Request> findByTeacherId(@Param("teacherId") String teacherId);


}