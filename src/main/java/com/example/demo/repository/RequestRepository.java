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
	
	List<Request> findByStudentUserIdOrderByRequestIdDesc(@Param("userId") String studentId);
	
	List<Request> findByTeacherUserIdOrderByRequestIdDesc(@Param("userId") String teacherId);

    @Modifying
	@Query("update Request v set v.consent = :consent where v.requestId = :requestId")
	int updateConsent(@Param("requestId") Integer requestId, @Param("consent") String consent);

    @Modifying
	@Query("update Request v set v.consent = :editConsent where v.requestDoc.requestDocId = :requestDocId and v.teacher.userId = :teacherId")
	int editRequest(@Param("requestDocId") Integer requestDocId, @Param("teacherId") String teacherId,@Param("editConsent") String editConsent);

    @Modifying
	@Query("update Request v set v.consent = :editConsent where v.reportDoc.reportDocId = :reportDocId and v.teacher.userId = :teacherId")
	int editReport(@Param("reportDocId") Integer reportDocId, @Param("teacherId") String teacherId,@Param("editConsent") String editConsent);

    
	@Query("select teacher.userName from Request where requestDoc.requestDocId = :requestDocId ORDER BY requestId")
	List<String> getTeacherNamesOfRequestDocId(@Param("requestDocId") Integer requestDocId);

	@Query("select teacher.userName from Request where reportDoc.reportDocId = :reportDocId ORDER BY requestId")
	List<String> getTeacherNamesOfReportDocId(@Param("reportDocId") Integer reportDocId);

	@Query("select reportDoc.reportDocId from Request where consent = '5' and docType = '1'")
	List<Integer> getLogReportDocIds();


}