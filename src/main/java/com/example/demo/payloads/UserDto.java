package com.example.demo.payloads;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank
	private String name;
	
	@Email(message = "Email address is not valid!")
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String about;
}
