package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.Ticket;

public interface TicketService {

	void addTicket(Ticket ticket);

	List<Ticket> getTicketListByTestId(Long testId);
}
