package com.thinktank.pts.qaservice.service;

import java.util.List;
import java.util.Optional;

import com.thinktank.pts.qaservice.model.Problem;

public interface ProblemService {

	Optional<Problem> getProblemById(Long id);

	void addProblem(Problem problem);

	List<Problem> getProblemListByTestId(Long testId);
}
