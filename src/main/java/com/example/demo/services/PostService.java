package com.example.demo.services;

import java.util.List;

import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto post , Integer categoryId , Integer userId);
	PostDto getPostById(Integer postId);
	PostDto updatePost(PostDto post , Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer PageNumber,Integer PageSize,String sortBy);
	//List<PostDto> getAllPost(Integer PageNumber,Integer PageSize);
	List<PostDto> getAllPostByCategory(Integer categoryId);
	List<PostDto> getAllPostByUser(Integer userId);
	List<PostDto> searchPostByKeyword(String keyword);
}
