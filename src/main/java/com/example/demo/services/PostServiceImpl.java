package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.PostRepo;
import com.example.demo.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Override
	public PostDto createPost(PostDto postdto , Integer categoryId , Integer userId) {
		User user = userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User" , "userId" , userId));
		Category cat = categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category" , "categoryId",categoryId));
		Post post = DtotoPost(postdto);
		post.setPostImageUrl("NewImage.jpg");
		post.setPostAddDate(new Date());
		post.setCategory(cat);
		post.setUser(user);
		Post savedPost = postrepo.save(post);
		return PosttoDto(savedPost);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "postId" , postId));
		return PosttoDto(post);
		
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "postId" , postId));
		post.setPostContent(postdto.getPostContent());
		post.setPostTitle(postdto.getPostTitle());
		post.setPostAddDate(postdto.getPostAddDate());
		post.setPostImageUrl(postdto.getPostImageUrl());
		return PosttoDto(post);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "postId" , postId));
		postrepo.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPost() {
		List<PostDto> listOfAllPosts = new ArrayList<>();
		List<Post> listPost = postrepo.findAll();
		for(Post post : listPost)
		{
			listOfAllPosts.add(PosttoDto(post));
		}
		return listOfAllPosts;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		List<PostDto> listOfAllPosts = new ArrayList<>();
		Category cat = categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category" , "categoryId",categoryId));
		List<Post> PostList = postrepo.findByCategory(cat);
		
		for (Post FetechedPost : PostList)
		{
			listOfAllPosts.add(PosttoDto(FetechedPost));
		}
		return listOfAllPosts;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		List<PostDto> listOfAllPosts = new ArrayList<>();
		User user_here = userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User" , "userId",userId));
		List<Post> PostList = postrepo.findByUser(user_here);
		
		for (Post FetechedPost : PostList)
		{
			listOfAllPosts.add(PosttoDto(FetechedPost));
		}
		return listOfAllPosts;
	}

	@Override
	public List<PostDto> searchPostByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Post DtotoPost(PostDto postdto)
	{
		return modelMapper.map(postdto, Post.class);
	}
	public PostDto PosttoDto(Post post)
	{
		return modelMapper.map(post, PostDto.class);
	}

}
