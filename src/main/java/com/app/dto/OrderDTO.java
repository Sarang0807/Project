package com.app.dto;

import com.app.pojos.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "userId")
public class OrderDTO {
	
	@JsonProperty("id")
	private Integer orderId;
  
	private User userId;

	private double totalPrize;
	
}
