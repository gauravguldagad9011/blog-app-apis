package com.codewithdurgesg.blog.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

//    3rs Step of jwt authentication. We can also copy that class from google. It is used to operate token
    public static final long JWT_TOKEN_VALIDITY=5*60*60;
    private String secret="jwtTokenKey";

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken (String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

//    check if the tokens are expired
    private Boolean isTokenExpired(String token){
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

//    while creating the token
//    1- Define claims of the token, like Issue,Expiration,subject and the id
//    2-sigloritn the JWT using the HS512 ahm and secret key
//    3- According to JWS compack serializationb(Https://toole.ietf.org?html?draft-ietf-jose)
//    Compaction  of the JWT to a URL safe string

    private String doGenerateToken(Map<String,Object>claims,String subject){
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()
            +JWT_TOKEN_VALIDITY*100))
            .signWith(SignatureAlgorithm.HS512,secret).compact();
    }

//    Validate Token
    public Boolean ValidateToken(String token,UserDetails userDetails){
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);

    }
}
