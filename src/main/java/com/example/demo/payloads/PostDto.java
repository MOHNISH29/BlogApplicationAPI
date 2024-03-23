package com.example.demo.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.entities.Category;
import com.example.demo.entities.Comment;
import com.example.demo.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@ManyToOne
	private CategoryDto category;
	
	@ManyToOne
	private UserDto user;
	
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<CommentDto> comments = new ArrayList<>();
	
	
}
