package com.github.cloudgyb.heartchat.service;

import com.github.cloudgyb.heartchat.domain.UserEntity;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * @author geng
 * @since 2023/03/01 22:10:27
 */
public class JJwtTokenTest {
    String encryptKey = "qwerTasdFzxcvfr1";

    @Test
    public void test() {
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setUsername("gyb");
        String token = generateToken(user);
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void testParse() {
        parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjaGF0U2VydmVyIiwiaWF0IjoxNjc3NjgwNjYwLCJzdWIiOiJneWIiLCJ1c2VySWQiOiIxIiwiZXhwIjoxNjgwMjcyNjYwfQ.YOiSnNuDk7JWWULWqduNCb9cv1iQbSqd3UpSPsOk-tY");
    }

    public boolean parseToken(String token) {
        Jwt jwt = Jwts.parser().setSigningKey(encryptKey).parse(token);
        return true;
    }

    private String generateToken(UserEntity user) {
        HashMap<String, Object> header = new HashMap<>();
        header.put(Header.TYPE, Header.JWT_TYPE);
        Date currDate = new Date();
        Date expireDate = new Date(currDate.getTime() + 30L * 24 * 60 * 60 * 1000);

        return Jwts.builder()
                .setHeader(header)
                .setIssuer("chatServer")
                .setIssuedAt(currDate)
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, encryptKey)
                .compact();
    }
}
