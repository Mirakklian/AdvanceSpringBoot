package com.rest.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DogOwnerDetailsViewDto {
	
	private Long dogId;
	
	private String dogRegNo;
	
	private String dogName;
	
	private Long ownerId;
	
	private String ownerName;
	
	private String ownerRegNo;
}
