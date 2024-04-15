package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;

@Component
public class JWTTokenHelper {
	public static final long TOKEN_VALIDITY = 5*60*60;
	SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode("cmdkmcoifjwifj9vs90dcdcddcsdwwwdwddd344343AAAAAmdmvkvdppzpppPP"));
	//SecretKey secret=Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded("secret");
	//private String key = "secret";
	
	//getting username from token
	public String getUserNameFromToken(String token)
	{
		return getClaimFromToken(token , Claims::getSubject);
	}
	
	//getting expiry date of token
	public Date getExpiryDate(String token)
	{
		return getClaimFromToken(token , Claims::getExpiration);
	}
	
	//function to decide and return expiry date/username of the token
	public <T> T getClaimFromToken(String token , Function<Claims , T> claimToReturn)
	{
		final Claims claim = GetAllClaimsFromToken(token);
		return claimToReturn.apply(claim);
	}
	
	
	public Claims GetAllClaimsFromToken(String token)
	{
		return (Claims) Jwts.parser().verifyWith(secret).build().parseSignedClaims(token);
	}
	
	private Boolean ifTokenIsExpired(String token)
	{
		final Date expDate = getExpiryDate(token);
		return expDate.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails)
	{
		Map<String , Object>claims = new HashMap<>();
		
		return doGenerateToken(claims , userDetails.getUsername());
	}
	
	@SuppressWarnings("deprecation")
	public String doGenerateToken(Map<String , Object>claims , String subject)
	{
		return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*1))
                .signWith(secret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !ifTokenIsExpired(token));
    }
	
}
