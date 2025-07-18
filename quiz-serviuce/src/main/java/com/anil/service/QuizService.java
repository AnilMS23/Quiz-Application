package com.anil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anil.Feign.QuizInterface;
import com.anil.dao.QuizDao;
import com.anil.model.QuestionWrapper;
import com.anil.model.Quiz;
import com.anil.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface  quizInterface;

	public ResponseEntity<String> createQuiz(String categary , int numQ, String title)
	{
		List<Integer> questions=quizInterface.getQuestionsForQuiz(categary, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success" , HttpStatus.OK);
	}
//	public ResponseEntity<String> createQuiz(String categary , int numQ, String title) {
//	    System.out.println("Received: category=" + categary + ", numQ=" + numQ + ", title=" + title);
//	    ResponseEntity<List<Integer>> response = quizInterface.getQuestionsForQuiz(categary, numQ);
//	    if (response == null || response.getBody() == null) {
//	        throw new RuntimeException("Null response from question service");
//	    }
//	    List<Integer> questions = response.getBody();
//	    System.out.println("Questions received: " + questions);
//	    
//	    Quiz quiz = new Quiz();
//	    quiz.setTitle(title);
//	    quiz.setQuestionIds(questions);
//	    
//	    quizDao.save(quiz);
//	    
//	    return new ResponseEntity<>("Success", HttpStatus.OK);
//	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz=quizDao.findById(id).get();
		List<Integer> questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
	ResponseEntity<Integer> score=quizInterface.getScore(responses);
		return score;
	}

}
