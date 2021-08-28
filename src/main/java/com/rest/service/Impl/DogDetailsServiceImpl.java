package com.rest.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rest.Dto.DogDetailsRequestDto;
import com.rest.advice.LogExecutionTime;
import com.rest.entity.DogDetails;
import com.rest.entity.DogOwnerDetails;
import com.rest.exception.ApplicationException;
import com.rest.repository.DogDetailsRepository;
import com.rest.service.DogDetailsService;

@Service
public class DogDetailsServiceImpl implements DogDetailsService {
	
	private final DogDetailsRepository dogDetailsRepository;

	private Logger LOG;

	/**
	 * @author Pratik Dutta
	 * @apiNote Return Dog Details rom MySq Database
	 * @return DogDetails
	 *
	 */
	@Autowired
	public DogDetailsServiceImpl(DogDetailsRepository dogDetailsRepository, Logger LOG) {
		this.dogDetailsRepository = dogDetailsRepository;
		this.LOG = LOG;
	}


	@Override
	@LogExecutionTime
	public List<DogDetails> getDogDetails() {
		LOG.info("Retriving All Dog Details from Database:");
		return dogDetailsRepository.findAll();
	}


	@Override
	public void saveDogDetails(DogDetailsRequestDto request, DogOwnerDetails owner) {
		 LOG.debug("Save Dog Detals in database started.");
		 
		 if(dogDetailsRepository.findByRegNo(request.getRegNo()).isPresent()) {
			 throw new ApplicationException(HttpStatus.BAD_REQUEST,"Data Already Present for Reg Id:"+request.getRegNo());
		 }
		 
		 DogDetails detail = mappedDto(request);
		 detail.setDogOwner(owner);
		 
		 dogDetailsRepository.save(detail);
		 LOG.debug("Save Dog Detals in database completed.");
		
	}


	/**
	 * @param request
	 * @return
	 */
	private DogDetails mappedDto(DogDetailsRequestDto request) {
		DogDetails detail=new DogDetails();
		 detail.setDogName(request.getDogName());
		 detail.setRegNo(request.getRegNo());
		 detail.setAvailability(request.getAvailability());
		 detail.setDogAge(request.getDogAge());
		 detail.setDogColor(request.getDogColor());
		 detail.setVaccnitated(request.getVaccnitated());
		return detail;
	}

}
