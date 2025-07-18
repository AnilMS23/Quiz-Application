package com.anil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.model.Question;
import com.anil.model.QuestionWrapper;
import com.anil.model.Response;
import com.anil.service.QuestionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestion")
	public ResponseEntity<List<Question>> getAllQuestions() {	
		return questionService.getAllQuestions();
	}
	@GetMapping("categary/{categary}")
	public ResponseEntity<List<Question>> getQuestionsByCategary(@PathVariable String categary)
	{
		return questionService.getQuestionsByCategary(categary);
	}
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question)
	{
		return questionService.addQuestion(question);
	}
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
	(@RequestParam(value = "categary", required = false) String categaryName , @RequestParam(value = "numQ",required = false) Integer numQuestions ) {
		return questionService.getQuestionsForQuiz(categaryName,numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> QuestionIds)
	{
		return 	questionService.getQuestionFromId(QuestionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses) {
		return questionService.getScore(responses);
	}	
}
