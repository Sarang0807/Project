package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {
	
	private Integer categoryId;
	@NotBlank
	private String catName;
	@NotBlank
	private String catImage;
	
	
}
