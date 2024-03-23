package com.example.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CommentDto;
import com.example.demo.repositories.CommentRepo;
import com.example.demo.repositories.PostRepo;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto addComment(CommentDto commentdto, Integer postId) {
		Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "Post ID" , postId));
		Comment com = modelMapper.map(commentdto, Comment.class);
		com.setPost(post);
		Comment savedComment = commentrepo.save(com);
		
		return modelMapper.map(savedComment, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment" , "Comment ID" , commentId));
		commentrepo.delete(com);
		
	}

}
