package com.thinktank.pts.qaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.model.Ticket;
import com.thinktank.pts.qaservice.repository.TicketRepository;
import com.thinktank.pts.qaservice.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void addTicket(Ticket ticket) {
		if (ticket != null) {
			ticketRepository.save(ticket);
		}
	}

	@Override
	public List<Ticket> getTicketListByTestId(Long testId) {
		return ticketRepository.getTicketListByTestTestId(testId);
	}

}
