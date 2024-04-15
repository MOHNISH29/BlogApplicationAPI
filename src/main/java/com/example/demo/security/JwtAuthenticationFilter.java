package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailservice;
	
	@Autowired
	private JWTTokenHelper jwtHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	
		String requestoken = request.getHeader(HttpHeaders.AUTHORIZATION);
		HttpServletRequest httpRequest =  request;
		httpRequest.getHeaderNames().asIterator()
        .forEachRemaining(headerName -> System.out.println(headerName + ": " + httpRequest.getHeader(headerName)));
		
		boolean NotNull = true;
		if(requestoken==null)
			NotNull = false;
		
		//Request Token looks like this --> "Bearer 334342mkdfk" ie it starts with Bearer
		String userName = null;
		if(request!=null && requestoken.startsWith("Bearer "))
		{
			String actualToken  = requestoken.substring(7);
			try {
				userName = jwtHelper.getUserNameFromToken(actualToken);
			}
			catch(IllegalArgumentException e)
			{
				System.out.print("Unable to get Jwt token");
			}
			catch(ExpiredJwtException e)
			{
				System.out.print("JWT token is expired");
			}
			catch(MalformedJwtException e)
			{
				System.out.print("Invalid JWT token");
			}
			
		}
		else
		{
			System.out.print("Jwt Token is not starting with Bearer");
			
		}
		
		//Here we are checking with SecurityContextHolder that whether authentication is already provided to
		//the user or not , if authentication is not provided ie. getAuthentication is null in this case
		//then we will redirect to a page ( can be a login page ) to get authenticated
		//Here we are creating an authentication with user details and setting it in Security Context
		//so that the particular user gets access to Protected Content
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = userDetailservice.loadUserByUsername(userName);
			if(jwtHelper.validateToken(requestoken, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else
			{
				System.out.print("Invalid Token");
			}
		}
		else
		{
			System.out.print("User Name is NUll");
		}
		
		//Tokens which dont have "Bearer" in their name can still pass throught this filter
		//But valid tokens will get validated as well as authneticated using above logic
		filterChain.doFilter(request, response);
		
	}

}
