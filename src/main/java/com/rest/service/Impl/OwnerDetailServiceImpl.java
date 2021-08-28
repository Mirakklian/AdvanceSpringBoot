package com.rest.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rest.entity.DogOwnerDetails;
import com.rest.exception.ApplicationException;
import com.rest.repository.DogOwnerDetailRepository;
import com.rest.service.DogOwnerDetailService;

@Service
public class OwnerDetailServiceImpl implements DogOwnerDetailService{
	
	@Autowired
	DogOwnerDetailRepository ownerRepository;

	@Override
	public DogOwnerDetails getDogOwner(String name, String regNo) {
		Optional<DogOwnerDetails> optionalOwner=ownerRepository.findByNameAndRegistrationNo(name, regNo);
		
		if(optionalOwner.isPresent()) {
			return optionalOwner.get();
		}
		else {
			throw new ApplicationException(HttpStatus.BAD_REQUEST,"Wrong Name and Registration No");
		}
	}

}
