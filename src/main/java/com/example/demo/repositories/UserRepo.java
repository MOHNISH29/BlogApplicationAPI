package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;
import com.example.demo.payloads.UserDto;

//we are using interface here to declare some abstract methods here that we will
//use in subclass
public interface UserRepo extends JpaRepository<User , Integer>{

	
	
}
