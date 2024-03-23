package com.example.demo.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payloads.PostDto;


public interface ImageUploadService {
	
	
	
	public PostDto uploadthisImage(MultipartFile file , Integer postId) throws IOException;
	

}
