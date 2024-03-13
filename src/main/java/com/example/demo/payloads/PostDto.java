package com.example.demo.payloads;

import java.util.Date;

import com.example.demo.entities.Category;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;
	
	@NotBlank
	private String postContent;
	
	@NotBlank
	private String postTitle;
	private Date postAddDate;
	private String postImageUrl;
	
	
	
}
