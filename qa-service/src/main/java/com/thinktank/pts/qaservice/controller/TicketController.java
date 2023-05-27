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
import com.thinktank.pts.qaservice.dto.TicketDTO;
import com.thinktank.pts.qaservice.mapper.TicketMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.Ticket;
import com.thinktank.pts.qaservice.service.TestService;
import com.thinktank.pts.qaservice.service.TestStepService;
import com.thinktank.pts.qaservice.service.TicketService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "tickets", description = "API for CRUD operations on tickets.")
@RequestMapping("/tickets")
@Transactional
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private TestService testService;

	@Autowired
	private TestStepService testStepService;

	private TicketMapper mapper = new TicketMapper();

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTicket(@RequestBody TicketDTO ticketDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			Ticket ticket = mapper.mapToEntity(ticketDTO);

			Test test = testService.getTestById(ticketDTO.getTestId());
			ticket.setTest(test);

			TestStep testStep = testStepService.getTestStepById(ticketDTO.getTestStepId());
			ticket.setTestStep(testStep);

			ticketService.addTicket(ticket);

			message = "Ticket: " + ticket.getId() + " created successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not create the ticket: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@GetMapping
	public List<TicketDTO> getTicketListByTestId(@RequestParam(name = "testId") Long testId) {
		return ticketService.getTicketListByTestId(testId).stream().map(mapper::mapToDTO).collect(Collectors.toList());
	}
}
