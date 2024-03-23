package com.example.demo.payloads;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
	private int commentId;
	private String commentContent;
	
	@ManyToOne
	private UserDto user;
	
	@ManyToOne
	private PostDto post;
}
