package com.rest.Dto;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorResponseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9108614029882166203L;

	private static final FastDateFormat isoFormat=FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
	
	private transient Map<String,Object> error;


	public ErrorResponseDto(HttpStatus errorCode, String corId, String message) {
		this(errorCode,corId,new Date(),message);
	}


	public ErrorResponseDto(HttpStatus errorCode, String corId, Date date, String message) {

		error=new LinkedHashMap<>();
		error.put("errorCode", errorCode);
		error.put("corId", corId);
		error.put("errorDateTime", isoFormat.format(date));
		error.put("errorMessage", message);
	}
	
	
}
