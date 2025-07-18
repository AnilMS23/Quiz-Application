package com.anil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.model.QuestionWrapper;
import com.anil.model.QuizDto;
import com.anil.model.Response;
import com.anil.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
		return quizService.createQuiz(quizDto.getCategaryName(), quizDto.getNumQuestion(), quizDto.getTitle());
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam(value = "id") Integer id) {
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id,@RequestBody List<Response> responses)
	{
		return quizService.calculateResult(id, responses);
	}
	
}
