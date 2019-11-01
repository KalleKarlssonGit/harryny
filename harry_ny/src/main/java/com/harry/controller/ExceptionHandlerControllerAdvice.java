package com.harry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.harry.exception.ExceptionResponse;
import com.harry.exception.HarryEmptyException;
import com.harry.exception.HarryServiceException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(HarryEmptyException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final HarryEmptyException exception, final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}

	@ExceptionHandler(HarryServiceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception, final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());

		return error;
	}

}