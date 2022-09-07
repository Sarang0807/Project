package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
public class Address  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	@Column(length = 20)
	private String city;
	@Column(length = 20)
	private String state;
	@Column(length = 10)
	private int pincode;
	@Column(length = 100)
	private String address;
	// bi dir one to one relationship between entities
	//val of mappedBy -- name of the asso. prop as it appears in the owning side
	@OneToOne(mappedBy ="addressid")
	private User user;
	
}
