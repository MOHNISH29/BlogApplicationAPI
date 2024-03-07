package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepo;

@Service
public  class UserServiceImpl implements UserService{

	//getting object of user Repo here to access data base
	//thus inorder to have methods of repo class injected in this class we have used @autowired annotation here
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = dtoToUser(userDto);
		User savedUser  = userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User" , "Id" , userId)));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User" , "Id" , userId)));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		List<User> users = userRepo.findAll();
		List<UserDto> userdtos = new ArrayList<>();
		
		for(int i = 0 ; i<users.size() ; i++)
		{
			userdtos.add(userToDto(users.get(i)));
		}
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow((()->new ResourceNotFoundException("User" , "Id" , userId)));
		userRepo.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto)
	{
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		
		return modelMapper.map(userDto, User.class);
		
	}
	
	public UserDto userToDto(User user)
	{
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return modelMapper.map(user, UserDto.class);
		
	}

}
