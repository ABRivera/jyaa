package rest.resources;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import model.Usuario;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;


public class AuthService {
	/* Create key. Usually it would be read from your appConfig.*/
	private static final String secretKeyString = "";
	
	public static String buildJWToken(Usuario user, Date expiration) throws WeakKeyException, UnsupportedEncodingException {
		Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes("UTF-8"));
		System.out.println("key de buildJWTOken:");
		System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
		String token = Jwts.builder().setId("BALP").setSubject(user.getName())
				.claim("rol", user.getRol().getPerfil())
				.claim("userid",user.getId())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(secretKey)
				.compact();
		return token;//"Bearer " + token;
	}
	
	public static Boolean validateToken(String jws) throws UnsupportedEncodingException {
	//verify jwt
		try {
			Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes("UTF-8"));
			System.out.println("key de validateTOken:");
			System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));
		    Jwts.parser().setAllowedClockSkewSeconds(120).setSigningKey(secretKey).parseClaimsJws(jws);//.getBody()
		    return true;
		    /*if claim assertions fail (throw exceptions)
	
		} catch (JwtException e) {
			e.printStackTrace();
			return false;
		    //JWT no confiable
		}
	
	}

}