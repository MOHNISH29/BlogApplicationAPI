package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.CommentDto;
import com.example.demo.payloads.DeleteResponse;
import com.example.demo.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/posts/{postId}/comment")
	public ResponseEntity<CommentDto> AddComment(@RequestBody CommentDto commDto , @PathVariable Integer postId)
	{
		CommentDto commentDto = commentservice.addComment(commDto, postId);
		return new ResponseEntity<CommentDto>(commentDto , HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<DeleteResponse> DeleteComment(@PathVariable Integer commentId)
	{
		commentservice.deleteComment(commentId);
		return new ResponseEntity<DeleteResponse>(new DeleteResponse("Comment is Deleted!") , HttpStatus.OK);
	}
}
