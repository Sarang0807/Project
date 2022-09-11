
package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.dto.UserDTO;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController // MANDATORY : composed of @Controller at the cls level + @ResponseBody(for
// marshalling : java ---> json) addedd implicitly on ret types of all req
// handling methods , annotated by @ReqMapping / @GetMapping .......
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {
	@Autowired
	private IUserService userService;


	public UserController() {
		System.out.println("in ctor of " + getClass());
	}

// add req handling method (REST API call) to send all users
	@GetMapping
	public ResponseEntity<?> listAllusers() {
		System.out.println("in list Users");
		List<User> list = userService.getAllUserDetails();
// o.s.ResponseEntity(T body,HttpStatus sts)
		if (list.isEmpty())
			return new ResponseEntity<>("Empty User List !!!!", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<UserDTO> saveUserDetails(@RequestBody @Valid UserDTO user)
	{
		System.out.println("in save User " + user);// id : null...
		return new ResponseEntity<>(userService.saveUserDetails(user), HttpStatus.CREATED);
	}

	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid User user) 
    {

            Optional<User> validateuser=userService.signIn(user);
            if(validateuser.isEmpty())
            {
                ErrorResponse resp=new ErrorResponse("Enter Valid Email or Password", LocalDateTime.now());
                return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(validateuser.get(),HttpStatus.OK);
    }
	

	@DeleteMapping("/{userId}")
	public String deleteUserDetails(@PathVariable @Range(min = 1, message = "Invalid user id!!!") int userId) {
		System.out.println("in del User " + userId);
		return userService.deleteUserDetails(userId);
	}

// add a method to get specific User dtls
	@GetMapping("/{id}")
// @PathVariable => a binding between a path var to method arg.
	public ResponseEntity<?> getUserDetails(@PathVariable int id) {
		System.out.println("in get User " + id);
		User user = userService.getUserDetails(id);
		System.out.println("User class " + user.getClass());
		return ResponseEntity.ok(user);

	}

// add a method to update existing resource
	@PutMapping
	public User updateUserDetails(@RequestBody @Valid User user) {
		System.out.println("in update User " + user);// id not null
		return userService.updateUserDetails(user);
	}


}