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
	@Query(value = "from ReportDoc where reportDocId IN :reportDocIds ")
	List<ReportDoc> getLogReports(@Param("reportDocIds") List<Integer> reportDocIds);

	ReportDoc findByReportDocId(@Param("reportDocId") Integer reportDocId);	


}