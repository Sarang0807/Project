
package com.app.controller;

import java.io.IOException;
import java.util.List;

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

import com.app.dto.CategoryDTO;
import com.app.pojos.Category;
import com.app.service.ICatService;

@RestController // MANDATORY : composed of @Controller at the cls level + @ResponseBody(for
// marshalling : java ---> json) addedd implicitly on ret types of all req
// handling methods , annotated by @ReqMapping / @GetMapping .......
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CategoryController {
	@Autowired
	private ICatService catService;


	public CategoryController() {
		System.out.println("in ctor of " + getClass());
	}

// add req handling method (REST API call) to send all users
	@GetMapping
	public ResponseEntity<?> listAllCategories() {
		System.out.println("in list categories");
		List<Category> list = catService.getAllCategoryDetails();
// o.s.ResponseEntity(T body,HttpStatus sts)
		if (list.isEmpty())
			return new ResponseEntity<>("Empty User List !!!!", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

// add req handling method to create new user
	@PostMapping
	public ResponseEntity<CategoryDTO> saveCategoryDetails(@RequestBody CategoryDTO cat)
// To inform SC , to un marshall(de-serialization , json/xml --> Java obj) the
// method arg.
	{
		System.out.println("in save Category " + cat);// id : null...

		//return  ResponseEntity.ok(userService.saveUserDetails(user))
		return new ResponseEntity<>(catService.saveCategoryDetails(cat), HttpStatus.CREATED);
	}

	
// add req handling method to delete user details
	@DeleteMapping("/{userId}") // can use ANY name for a path var.
// @PathVariable => a binding between a path var to method arg.
	public String deleteCategoryDetails(@PathVariable @Range(min = 1, message = "Invalid Category id!!!") int catId) {
		System.out.println("in del emp " + catId);
		return catService.deleteCategoryDetails(catId);
	}

// add a method to get specific User dtls
	@GetMapping("/{id}")
// @PathVariable => a binding between a path var to method arg.
	public ResponseEntity<?> getCategoryDetails(@PathVariable int id) {
		System.out.println("in get Category " + id);
		Category cat = catService.getCategoryDetails(id);
		System.out.println("emp class " + cat.getClass());
		return ResponseEntity.ok(cat);

	}

// add a method to update existing resource
	@PutMapping
	public Category updateCategoryDetails(@RequestBody Category cat) {
		System.out.println("in update Category " + cat);// id not null
		return catService.updateCategoryDetails(cat);
	}


	
	
	
	
	
	
	
	// add a method to upload image on the server side folder
		@PostMapping("/{empId}/image")
		public ResponseEntity<?> uploadImage(@PathVariable int empId, @RequestParam MultipartFile imageFile)
				throws IOException {
			System.out.println("in upload image " + empId);
			System.out.println("uploaded img file name " + imageFile.getOriginalFilename() + " content type "
					+ imageFile.getContentType() + " size " + imageFile.getSize());
			// invoke service layer method to save uploaded file in the server side folder
			// --ImageHandligService
			CategoryDTO catDTO = catService.storeImage(empId, imageFile);
			return ResponseEntity.ok(catDTO);
		}

		// add req handling method to download image for specific emp
		@GetMapping(value = "/{empId}/image", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
				MediaType.IMAGE_PNG_VALUE })
		public ResponseEntity<?> downloadImage(@PathVariable int catId) throws IOException{
			System.out.println("in img download " + catId);
			//invoke service layer method , to get image data from the server side folder
			byte[] imageContents=catService.restoreImage(catId);
			return ResponseEntity.ok(imageContents);
		}
	
	
	
	
	
	
	
	
	
}
