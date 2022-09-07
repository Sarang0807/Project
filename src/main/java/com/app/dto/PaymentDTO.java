package com.app.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.app.pojos.Order;
import com.app.pojos.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(exclude = "userId")
@Table(name="payments")
public class PaymentDTO {

	private Integer paymentId;
	  
	  private User userId;

	  private Order orderId;
		@NotBlank
	 private double totalPrize;
	
}
