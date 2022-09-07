package com.app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
@Table(name="orders")
public class OrderDTO {
	
	private Integer orderId;
  
	private User userId;
	@NotBlank
	private double totalPrize;
	
}
