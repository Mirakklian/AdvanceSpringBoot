package com.rest.service;

import com.rest.entity.DogOwnerDetails;

public interface DogOwnerDetailService {
	
	DogOwnerDetails getDogOwner(String name,String regNo);

}
