package com.thinktank.pts.qaservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.ProblemDTO;
import com.thinktank.pts.qaservice.mapper.ProblemMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Problem;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.service.ProblemService;
import com.thinktank.pts.qaservice.service.TestService;
import com.thinktank.pts.qaservice.service.TestStepService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "problem", description = "API for CRUD operations on problems.")
@RequestMapping("/problems")
@Transactional
public class ProblemController {

	@Autowired
	private ProblemService problemService;

	@Autowired
	private TestService testService;

	@Autowired
	private TestStepService testStepService;

	private ProblemMapper mapper = new ProblemMapper();

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addProblem(@RequestBody ProblemDTO problemDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			Problem problem = mapper.mapToEntity(problemDTO);

			Test test = testService.getTestById(problemDTO.getTestId());
			problem.setTest(test);

			TestStep testStep = testStepService.getTestStepById(problemDTO.getTestStepId());
			problem.setTestStep(testStep);

			problemService.addProblem(problem);

			message = "Problem: " + problem.getId() + " created successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not create the problem: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@GetMapping
	public List<ProblemDTO> getProblemListByTestId(@RequestParam(name = "test") Long testId) {
		return problemService.getProblemListByTestId(testId).stream().map(mapper::mapToDTO)
				.collect(Collectors.toList());
	}
}
