package com.anil.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anil.dao.QuestionDao;
import com.anil.model.Question;
import com.anil.model.QuestionWrapper;
import com.anil.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		try
		{
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<List<Question>> getQuestionsByCategary(String categary)
	{
		try
		{
			return new ResponseEntity<>(questionDao.findByCategary(categary) , HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> addQuestion(Question question)
	{
		questionDao.save(question);
		return new ResponseEntity<>("success" , HttpStatus.CREATED);
	}

//	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categaryName, Integer numQuestions) {
//		Pageable pageable = PageRequest.of(0, numQuestions);
//		List<Question> questions = questionDao.findByCategary(categaryName,pageable);       		     		
//		List<Integer> questionIds = questions.stream().map(Question::getId).collect(Collectors.toList());
//		
//		return new ResponseEntity<>(questionIds , HttpStatus.OK);
//	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categaryName, Integer numQuestions) {
	    List<Question> allQuestions = questionDao.findByCategary(categaryName); // 1

	    Collections.shuffle(allQuestions); // 2

	    List<Integer> questionIds = allQuestions.stream() // 3
	            .limit(numQuestions)                      // 4
	            .map(Question::getId)                     // 5
	            .collect(Collectors.toList());            // 6

	    return new ResponseEntity<>(questionIds, HttpStatus.OK); // 7
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		for(Integer id:questionIds)
		{
			questions.add(questionDao.findById(id).get());
		}
		for(Question question:questions)
		{
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuetionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers , HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		Integer right=0;
		for(Response response: responses)
		{
			Question question=questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
			{
				right++;
			}
		}
		return new ResponseEntity<>(right , HttpStatus.OK);
	}
	


}
