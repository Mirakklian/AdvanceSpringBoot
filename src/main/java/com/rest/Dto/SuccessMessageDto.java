package com.rest.Dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SuccessMessageDto{

	private String status;
	
	private String message;

}
