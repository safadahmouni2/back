package com.thinktank.pts.qaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.Ticket;

@RepositoryRestResource
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Override
	Optional<Ticket> findById(Long id);

	List<Ticket> getTicketListByTestTestId(Long testId);
}
