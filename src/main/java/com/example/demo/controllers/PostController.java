package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.PostDto;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@PathVariable Integer userId ,@PathVariable Integer categoryId ,@RequestBody PostDto posts)
	{
		PostDto postCreated = postService.createPost(posts, categoryId, userId);
		return new ResponseEntity<PostDto>(postCreated , HttpStatus.CREATED);
	}
	
	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> postGotFromCategory = postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postGotFromCategory , HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUsers(@PathVariable Integer userId)
	{
		List<PostDto> postGotFromUser = postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postGotFromUser , HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId)
	{
		PostDto postNeeded = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postNeeded , HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPostsHere()
	{
		List<PostDto> AllPosts = postService.getAllPost();
		return ResponseEntity.ok(AllPosts);
	}
	
	
	
	
}
