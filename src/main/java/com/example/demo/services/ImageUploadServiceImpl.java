package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Post;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.repositories.PostRepo;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private ModelMapper modelMapper;

	@Value("${image.upload.path}")
	private String ImagePath;
	@Override
	public PostDto uploadthisImage(MultipartFile multiPartFile, Integer postId) throws IOException{
		String file_name = multiPartFile.getOriginalFilename();
		
	String random_id = UUID.randomUUID().toString();
	String new_file_name = random_id.concat(file_name.substring(file_name.lastIndexOf(".")));
		
		String file_path = ImagePath + File.separator + new_file_name;
		
		File f = new File(ImagePath);
		if(!f.exists())
		{
			f.mkdir();
		}
		Files.copy(multiPartFile.getInputStream(), Paths.get(file_path));
		
		
		Post post = postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post" , "postId" , postId));
		post.setPostImageUrl(new_file_name);
		Post savedPost = postrepo.save(post);
		return modelMapper.map(savedPost, PostDto.class);

	}

}
