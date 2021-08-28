/**
 * 
 */
package com.rest.Dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Pratik Dutta
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DogDetailsRequestDto {
		
	@NotNull(message = "Age Must Not be Null ")
	Integer dogAge;
	
	@NotNull(message = "Color Must Not be Null ")
	String dogColor;
	
	@NotNull(message = "Availability Must Not be Null ")
	Character availability;
	
	@NotNull(message = "Vaccnited Must Not be Null ")
	Character vaccnitated;
	
	@NotNull(message = "Name Must Not be Null ")
	String dogName;
	
	@NotNull(message = "Reg No Must Not be Null ")
	String regNo;

}
