package com.example.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//UserDto class here is created to not to expose our
//entity class directly to database or Repo class , we will use this class to transfer data from entity class


@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String about;
}
