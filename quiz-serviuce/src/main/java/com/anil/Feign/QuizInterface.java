package com.anil.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anil.model.QuestionWrapper;
import com.anil.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
	(@RequestParam(value = "categary",required = false) String categary , @RequestParam(value = "numQ",required = false) Integer numQuestions );
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> QuestionIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses);
}
