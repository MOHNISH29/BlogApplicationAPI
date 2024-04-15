package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.CustomUserDetailService;
import com.example.demo.security.JWTAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	

	@Autowired
	JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	    //@SuppressWarnings("removal")
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
		{
            http.
                    csrf(csrf -> csrf.disable()).authorizeHttpRequests(requests -> requests.requestMatchers("/api/v1/auth/reg")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST)
                    .permitAll()
                    .anyRequest()
                    .authenticated())
                    .exceptionHandling(handling -> handling
                            .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                    .sessionManagement(management -> management
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            

           http.addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);
            http.authenticationProvider(authenticationProvider());
            DefaultSecurityFilterChain build1 = http.build();
            
            return build1;
	    	        
	                
		}
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(customUserDetailService);
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;

	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
		
	
}
