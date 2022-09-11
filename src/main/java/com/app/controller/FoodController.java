
package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.FoodDTO;
import com.app.pojos.Food;
import com.app.service.IFoodService;

@RestController 
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class FoodController {
	@Autowired
	private IFoodService foodService;


	public FoodController() {
		System.out.println("in ctor of " + getClass());
	}


	@GetMapping
	public ResponseEntity<?> listAllFoods() {
		System.out.println("in list Foods");
		List<Food> list = foodService.getAllFoodDetails();

		if (list.isEmpty())
			return new ResponseEntity<>("Empty Food List !!!!", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@PostMapping("/{foodId}")
	public ResponseEntity<FoodDTO> saveFoodDetails(@PathVariable int foodId,@RequestBody @Valid FoodDTO food)
	{
		System.out.println("in save Food " + food);// id : null...
		return new ResponseEntity<>(foodService.saveFoodDetails(foodId,food), HttpStatus.CREATED);
	}

	
	@DeleteMapping("/{foodId}") 
	public String deleteFoodDetails(@PathVariable @Range(min = 1, message = "Invalid Food id!!!") int foodId) {
		System.out.println("in del food " + foodId);
		return foodService.deleteFoodDetails(foodId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFoodDetails(@PathVariable int id) {
		System.out.println("in get Food " + id);
		Food food = foodService.getFoodDetails(id);
		System.out.println("Food class " + food.getClass());
		return ResponseEntity.ok(food);

	}

// add a method to update existing resource
	@PutMapping
	public Food updateFoodDetails(@RequestBody Food food) {
		System.out.println("in update Food " + food);// id not null
		return foodService.updateFoodDetails(food);
	}

	
		@PostMapping("/{foodId}/image")
		public ResponseEntity<?> uploadImage(@PathVariable int foodId, @RequestParam MultipartFile imageFile)
				throws IOException {
			System.out.println("in upload image " + foodId);
			System.out.println("uploaded img file name " + imageFile.getOriginalFilename() + " content type "
					+ imageFile.getContentType() + " size " + imageFile.getSize());
			FoodDTO foodDTO = foodService.storeImage(foodId, imageFile);
			return ResponseEntity.ok(foodDTO);
		}


		@GetMapping(value = "/{foodId}/image", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
				MediaType.IMAGE_PNG_VALUE })
		public ResponseEntity<?> downloadImage(@PathVariable int foodId) throws IOException{
			System.out.println("in img download " + foodId);
			byte[] imageContents=foodService.restoreImage(foodId);
			return ResponseEntity.ok(imageContents);
		}
	
	
	
	
	
	
	
	
	
}
