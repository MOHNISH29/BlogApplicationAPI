package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payloads.ApiResponse;
import jakarta.validation.constraints.NotBlank;

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
		
		@ExceptionHandler (MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String ,String>> NotCorrectDataException(MethodArgumentNotValidException ex)
		{
			Map<String , String> error_messages = new HashMap<>();
			BindingResult res = ex.getBindingResult();
			List<FieldError> fieldErrors = res.getFieldErrors();
			
			for(FieldError errors : fieldErrors )
			{
				error_messages.put(errors.getField(), errors.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String , String>>(error_messages , HttpStatus.BAD_REQUEST);
		}
}
