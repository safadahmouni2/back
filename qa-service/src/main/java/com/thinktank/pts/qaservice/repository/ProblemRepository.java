package com.thinktank.pts.qaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.Problem;

@RepositoryRestResource
@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

	@Override
	Optional<Problem> findById(Long id);

	List<Problem> getProblemListByTestTestId(Long testId);
}
