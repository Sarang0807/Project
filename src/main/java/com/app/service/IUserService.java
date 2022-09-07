package com.app.service;

import java.util.List;

import com.app.dto.UserDTO;
import com.app.pojos.User;



public interface IUserService {

	//get all details
	  List<User> getAllUserDetails();
	//save new user details
	  UserDTO saveUserDetails(UserDTO user); 
	//delete user details
	  String deleteUserDetails(int userId);
	//get user details by specified id
	  User getUserDetails(int userId);
	//update existing user details
	  User updateUserDetails(User updatedUser);
	 
}
