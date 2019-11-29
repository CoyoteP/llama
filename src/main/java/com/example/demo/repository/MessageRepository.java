package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ChatList;
import com.example.demo.model.Message;
import com.example.demo.model.ReportDoc;
import com.example.demo.model.User;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

	@Query("select u.userId,u.userName, m.sendDate, m.message from User u left join u.messagesOfTeacher m on u.userId = m.teacher.userId and m.student.userId = :studentId and m.messageId in (select m1.messageId from Message m1 WHERE NOT EXISTS (SELECT m2.messageId FROM Message m2 WHERE m1.student.userId = m2.student.userId and m1.teacher.userId = m2.teacher.userId and m1.sendDate < m2.sendDate))where u.role = 'TEACHER' order by m.sendDate desc NULLS LAST")
	String[][] getListByStudent(@Param("studentId") String studentId);

	@Query("select u.userId,u.userName, m.sendDate, m.message from User u left join u.messagesOfStudent m on u.userId = m.student.userId and m.teacher.userId = :teacherId and m.messageId in (select m1.messageId from Message m1 WHERE NOT EXISTS (SELECT m2.messageId FROM Message m2 WHERE m1.student.userId = m2.student.userId and m1.teacher.userId = m2.teacher.userId and m1.sendDate < m2.sendDate))where u.role = 'STUDENT' order by m.sendDate desc NULLS LAST")
	String[][] getListByTeacher(@Param("teacherId") String teacherId);

	List<Message> findByStudentUserIdAndTeacherUserId(@Param("studentId") String studentId, @Param("teacherId")String teacherId);



}