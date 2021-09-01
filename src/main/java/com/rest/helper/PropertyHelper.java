package com.rest.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PropertyHelper {

	@Value("${dog.owner.alldetails.url}")
	private String getAllDogOwnerDetailsUrl;
}
