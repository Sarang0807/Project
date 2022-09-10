package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.pojos.Order;
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
public class PaymentDTO {

	@JsonProperty("id")
	private Integer paymentId;
	  
	  private User userId;

	  private Order orderId;
		@NotBlank
	 private double totalPrize;
	
}
