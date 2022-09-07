package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AddressDTO  {
	
	private Integer addressId;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	@NotBlank
	private int pincode;
	@NotBlank
	private String address;
	
}
