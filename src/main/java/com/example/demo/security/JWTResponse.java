package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTResponse {

	private String TokenResponse;
	
	public JWTResponse(String TokenResponse) 
	{
		this.TokenResponse = TokenResponse;
	}
}
