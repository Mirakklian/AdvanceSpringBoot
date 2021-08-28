package com.rest.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6080911860308101202L;

	protected final  HttpStatus errorCode;
	
//	protected final  String corId;
//	
//	private  Long errorTimestamp;
	
	protected final  String message;

	

	

}
