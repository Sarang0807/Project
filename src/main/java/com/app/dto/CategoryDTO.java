package com.app.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name="categories")
public class CategoryDTO {
	
	private Integer categoryId;
	@NotBlank
	private String catName;
	@NotBlank
	private String catImage;
	
	
}
