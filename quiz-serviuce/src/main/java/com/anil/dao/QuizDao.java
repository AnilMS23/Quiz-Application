package com.anil.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anil.model.Quiz;
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
