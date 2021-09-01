package com.rest.service;

import java.util.List;

import com.rest.Dto.DogOwnerDetailsViewDto;

public interface DogOwnerService {
	
	List<DogOwnerDetailsViewDto> retriveAllDogOwnerDetails();

}
