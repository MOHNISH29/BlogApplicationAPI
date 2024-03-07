package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/users")

//Here controller is mapped to /api/users type of requests
public class UserController {

	@Autowired
	private UserService userservice;
	
	//POST - CREATE USER
	//When we will get Post request it will trigger this function to create a new user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createduserDto = userservice.createUser(userDto);
		
		return new ResponseEntity<>(createduserDto , HttpStatus.CREATED);
	}
	
	//Passing userID that needs to be updated with data present in object of user which is passed in json
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto , @PathVariable Integer userId)
	{
		UserDto updateduserDto = userservice.updateUser(userDto, userId);
		return ResponseEntity.ok(updateduserDto);
			 
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId)
	{
		userservice.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
	{
		UserDto requireduser = userservice.getUserById(userId);
		return ResponseEntity.ok(requireduser);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> ListOfAllUsers = userservice.getAllusers();
		return ResponseEntity.ok(ListOfAllUsers);
	}
	
}
