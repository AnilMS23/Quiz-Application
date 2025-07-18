package com.anil.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anil.model.Question;

@Repository
public interface QuestionDao  extends JpaRepository<Question, Integer>{

List<Question> findByCategary(String categary);

List<Question> findByCategary(String categary,Pageable pageable);

	}
