package com.thinktank.pts.qaservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.model.Problem;
import com.thinktank.pts.qaservice.repository.ProblemRepository;
import com.thinktank.pts.qaservice.service.ProblemService;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;

	@Override
	public void addProblem(Problem problem) {
		if (problem != null) {
			problemRepository.save(problem);
		}
	}

	@Override
	public Optional<Problem> getProblemById(Long id) {
		return problemRepository.findById(id);
	}

	@Override
	public List<Problem> getProblemListByTestId(Long testId) {
		return problemRepository.getProblemListByTestTestId(testId);
	}

}
