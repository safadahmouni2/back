package com.thinktank.pts.qaservice.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class PtsImportException extends RuntimeException {

	private String message;

	private HttpStatus httpStatus;

	private ZonedDateTime timeStamp;

	public PtsImportException(String message) {
		this.message = message;
	}
	public PtsImportException(HttpStatus httpStatus, String message, ZonedDateTime timeStamp) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.timeStamp = timeStamp;
	}

    @Override
    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

}
