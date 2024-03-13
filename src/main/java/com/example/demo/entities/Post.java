package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="posts")

@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer postId;
	
	private String postContent;
	private String postTitle;
	private Date postAddDate;
	private String postImageUrl;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	
	
}
