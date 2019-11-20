package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ReportDoc;
import com.example.demo.model.User;

@Transactional
@Repository
public interface ReportDocRepository extends JpaRepository<ReportDoc, String> {

	@Modifying
	@Query(value = "select * from report_doc where report_doc_id = IN(SELECT doc_id from request where doc_type = '6') ", nativeQuery = true)
	List<ReportDoc> getLogReports();

	ReportDoc findByReportDocId(@Param("reportDocId") String reportDocId);	


}