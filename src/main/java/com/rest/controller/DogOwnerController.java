package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.DogOwnerDetailsViewDto;
import com.rest.service.DogOwnerService;

@RestController
public class DogOwnerController {
	
	@Autowired
	private DogOwnerService dogOwnerService;
	
	@GetMapping(value = "/v1/dog-ngo/all-dogowner")
	public ResponseEntity<List<DogOwnerDetailsViewDto>> getAllDogOwnderDetails(){
		return new ResponseEntity<>(dogOwnerService.retriveAllDogOwnerDetails(),
				HttpStatus.OK);
	}

}
