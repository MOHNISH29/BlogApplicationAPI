package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.CategoryRepo;
import com.example.demo.repositories.UserRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		Category cat = dtoToCategory(category);
		Category savedCategory = categoryRepo.save(cat);
		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "Category Id" , categoryId));
		cat.setCategoryType(category.getCategoryType());
		cat.setCategoryDescription(category.getCategoryDescription());
		Category updatedCategory = categoryRepo.save(cat);
		return categoryToDto(updatedCategory);
	}
	

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category" , "Category Id" , categoryId)));
		categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId).orElseThrow((()->new ResourceNotFoundException("Category" , "Category Id" , categoryId)));
		return categoryToDto(cat);
	}

	@Override
	public List<CategoryDto> getAllcategories() {
		List<Category> cat = categoryRepo.findAll();
		List<CategoryDto> catdto  = new ArrayList<>();
		
		for(Category c : cat)
		{
			catdto.add(categoryToDto(c));
		}
		return catdto;
	}
	
	public Category dtoToCategory(CategoryDto categoryDto)
	{

	    return modelMapper.map(categoryDto, Category.class);
		
	}
	
	public CategoryDto categoryToDto(Category category)
	{

		return modelMapper.map(category, CategoryDto.class);
		
	}

}
