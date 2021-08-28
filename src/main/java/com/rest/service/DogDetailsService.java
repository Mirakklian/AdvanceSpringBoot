package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.Dto.DogDetailsRequestDto;
import com.rest.entity.DogDetails;
import com.rest.entity.DogOwnerDetails;

@Service
public interface DogDetailsService {
	
	List<DogDetails> getDogDetails();
	
	void saveDogDetails(DogDetailsRequestDto response, DogOwnerDetails owner);

}
