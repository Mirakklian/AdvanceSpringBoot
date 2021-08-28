/**
 * 
 */
package com.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Dto.DogDetailsRequestDto;
import com.rest.Dto.SuccessMessageDto;
import com.rest.advice.LogExecutionTime;
import com.rest.entity.DogDetails;
import com.rest.entity.DogOwnerDetails;
import com.rest.exception.ApplicationException;
import com.rest.service.DogDetailsService;
import com.rest.service.DogOwnerDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * @author Pratik Dutta
 *
 */

@RestController
@Validated
public class DogDetailsController {

	private final DogDetailsService dogDetailsService;
	
	private final DogOwnerDetailService ownerService;

	private Logger LOG;

	
	@Autowired
	public DogDetailsController(DogDetailsService dogDetailsService,
			DogOwnerDetailService ownerService, Logger LOG) {
		this.dogDetailsService = dogDetailsService;
		this.ownerService = ownerService;
		this.LOG = LOG;
	}
	
	/**
	 * @author Pratik Dutta
	 * @apiNote Return Dog Details 
	 * @return DogDetails as List
	 *
	 */

	@GetMapping(path = "v1/dog-ngo/all-details")
	@Operation(summary="Get All Dog Details",
				description="Download All Dog Details from Database with Dog Id an Registration Number")
	@ApiResponse(responseCode="200", content=@Content(mediaType = "application/json",
									schema=@Schema(anyOf = DogDetails.class)))
	@LogExecutionTime
	public ResponseEntity<List<DogDetails>> getDogDetails(HttpServletRequest request) {
		
			LOG.info("Requesting for all Dogs Details in Controller:");
			return new ResponseEntity<>(dogDetailsService.getDogDetails(), HttpStatus.OK);
	}
	
	
	
	@PostMapping(path = "v1/dog-ngo/detail")
	@Operation(summary="Save Dog Detail",
				description="Save Dog Detail in Database")
	@ApiResponse(responseCode="201", content=@Content(mediaType = "application/json",
									schema=@Schema(anyOf = SuccessMessageDto.class)))
	@LogExecutionTime
	public ResponseEntity<SuccessMessageDto> saveDogDetail(HttpServletRequest request, @Valid @RequestBody DogDetailsRequestDto dogDetailsRequestDto) {
			try {	
				LOG.info("Requesting for Save Details with name: {}",dogDetailsRequestDto.getDogName());
				
				String ownerName=request.getHeader("ownerName");
				String regNo=request.getHeader("ownerRegNo");
				
				DogOwnerDetails owner=ownerService.getDogOwner(ownerName, regNo);
				
				dogDetailsService.saveDogDetails(dogDetailsRequestDto, owner);
				SuccessMessageDto response=new SuccessMessageDto("Success","Dog Details Saved Successfully");
				
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}
			catch(ApplicationException ex) {
				throw new ApplicationException(ex.getErrorCode(),ex.getMessage());
			}
	}
}
