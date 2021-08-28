package com.rest.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rest.Dto.ErrorResponseDto;
import com.rest.exception.ApplicationException;
import com.rest.util.Context;

@ControllerAdvice
public class ApiErrorHandler {
	
	
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDto> handleApplicationException(ApplicationException ex){
		ErrorResponseDto response=new ErrorResponseDto(ex.getErrorCode(),Context.transactionId(),ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex){
		ErrorResponseDto response=new ErrorResponseDto(HttpStatus.BAD_REQUEST,Context.transactionId(),ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(HttpServletRequest request,
			SQLIntegrityConstraintViolationException ex){
		ErrorResponseDto response=new ErrorResponseDto(HttpStatus.BAD_REQUEST,Context.transactionId(),ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(HttpServletRequest request,
			HttpMessageNotReadableException ex){
		ErrorResponseDto response=new ErrorResponseDto(HttpStatus.BAD_REQUEST,Context.transactionId(),ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}

}
