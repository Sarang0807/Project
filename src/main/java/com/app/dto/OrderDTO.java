package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.pojos.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "userId")
public class OrderDTO {
	
	private Integer orderId;
  
	private User userId;
	@NotBlank
	private double totalPrize;
	
}
