package com.rest.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.Dto.DogOwnerDetailsViewDto;
import com.rest.helper.PropertyHelper;
import com.rest.service.DogOwnerService;

@Service
public class DogOwnerServiceImpl implements DogOwnerService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PropertyHelper propertyHelper;

	@Override
	public List<DogOwnerDetailsViewDto> retriveAllDogOwnerDetails() {
		
		return 	restTemplate.exchange(propertyHelper.getGetAllDogOwnerDetailsUrl(), HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<DogOwnerDetailsViewDto>>() {}).getBody();
	}

}
