package com.app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.app.pojos.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")
@Entity
@Table(name="addresses")
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
	
	private User user;
	
}
