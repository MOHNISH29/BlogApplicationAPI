package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JWTRequest;
import com.example.demo.security.JWTResponse;
import com.example.demo.security.JWTTokenHelper;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JWTTokenHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/reg")
	public ResponseEntity<JWTResponse> createToken(@RequestBody JWTRequest request)
	{
		System.out.print("Inside this function");
		authenticateToken(request.getUsername() , request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String tokenFormed = jwtHelper.generateToken(userDetails);
		
		JWTResponse resp = new JWTResponse(tokenFormed);
		
		return ResponseEntity.ok(resp);
	}

	private void authenticateToken(String username, String password)
	{
		UsernamePasswordAuthenticationToken userPassToken = new UsernamePasswordAuthenticationToken(username,password);
		authenticationManager.authenticate(userPassToken);
	}

}
