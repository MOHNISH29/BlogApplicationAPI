package com.example.demo.payloads;



import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<CommentDto> comments = new ArrayList<>();
	
}
