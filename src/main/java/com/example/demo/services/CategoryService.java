package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.UserDto;


public interface CategoryService {

	CategoryDto createCategory(CategoryDto category);
	CategoryDto updateCategory(CategoryDto category , Integer categoryId);
	void deleteCategory(Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllcategories();
	
}
