package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	
	@JsonProperty(access = Access.READ_ONLY)
	private String catImage;
	
	
}
