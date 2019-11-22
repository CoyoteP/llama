package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.RequestDoc;
import com.example.demo.model.User;

@Transactional
@Repository
public interface RequestDocRepository extends JpaRepository<RequestDoc, String> {

	RequestDoc findByRequestDocId(@Param("requestDocId") Integer requestDocId);

}