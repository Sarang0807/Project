package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.pojos.Category;
import com.app.pojos.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FoodDTO {
	
	private Integer foodId;
	@NotBlank
	private String foodName;
	@NotBlank
	private String foodDescription;
	@NotBlank
	private String foodImage;
	@NotBlank
	private double foodPrize;
	        //fk
	private Category categoryId;
	        //fk
	private Order orderId;
	
	
	
}
