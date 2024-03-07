package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
		
		//whenever we ResourceNotFoundException will be called it will call this particular method too
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
		{
			String message = ex.getMessage();
			ApiResponse apiResponse = new ApiResponse(message , "false");
			
			return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
			
			
		}
}
