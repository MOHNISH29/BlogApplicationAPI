package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.DeleteResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
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
	public ResponseEntity<PostResponse> getAllPostsHere(@RequestParam(value="PageNumber",defaultValue="0",required=false)Integer PageNumber,
			@RequestParam(value="PageSize" , defaultValue="10" , required=false)Integer PageSize , 
			@RequestParam(value="sortBy" , defaultValue="id" , required=false) String sortBy)
	{
		PostResponse pResponse = postService.getAllPost(PageNumber,PageSize,sortBy);
		return ResponseEntity.ok(pResponse);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updateThePost(@RequestBody PostDto postdto , @PathVariable Integer postId)
	{
		PostDto postUpdated = postService.updatePost(postdto, postId);
		return ResponseEntity.ok(postUpdated);
		
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<DeleteResponse> deleteThePost(@PathVariable Integer postId)
	{
		postService.deletePost(postId);
		return new ResponseEntity<DeleteResponse>(new DeleteResponse("This post is deleted") , HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/search")
	public ResponseEntity<List<PostDto>> getAllpostsByKeyword(@RequestParam(value="Keyword" , required=true) String keyword)
	{
		List<PostDto> AllPostsByGivenKeyword=postService.searchPostByKeyword(keyword);
		return new ResponseEntity<List<PostDto>>(AllPostsByGivenKeyword , HttpStatus.OK);
		
	}
	
	
}
