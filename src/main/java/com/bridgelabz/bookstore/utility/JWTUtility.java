package com.bridgelabz.bookstore.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtility {
	private static final String SECRET="63000887";
	public static String jwtToken(Long userId)
	{
		String token=null;
		token=JWT.create().withClaim("id",userId).sign(Algorithm.HMAC256(SECRET));
		return token;
	}
	public static Long parseJWT(String jwt)
	{
		Long id=(long)0;
		if(jwt!=null)
		{
			
			id=JWT.require(Algorithm.HMAC256(SECRET)).build().verify(jwt).getClaim("id").asLong();
		}
		return id;
	}
}
