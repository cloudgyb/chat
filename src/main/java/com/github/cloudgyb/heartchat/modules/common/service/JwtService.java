package com.github.cloudgyb.heartchat.modules.common.service;

import com.github.cloudgyb.heartchat.config.JwtConfig;
import com.github.cloudgyb.heartchat.domain.UserEntity;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * json web token 服务
 *
 * @author geng
 * @since 2023/03/02 21:00:57
 */
@Service
public class JwtService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(UserEntity user) {
        HashMap<String, Object> header = new HashMap<>();
        header.put(Header.TYPE, Header.JWT_TYPE);
        Date currDate = new Date();
        Date expireDate = new Date(currDate.getTime() + jwtConfig.getDuration().getSeconds() * 1000);
        String encryptKey = jwtConfig.getSecretKey();
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

    @SuppressWarnings("rawtypes")
    public Jwt parseToken(String token) {
        Exception exception;
        try {
            return Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parse(token);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            exception = e;
            logger.error("无效的token：{}", token);
        } catch (SignatureException e) {
            exception = e;
            logger.error("token '{}' 签名无效！", token);
        } catch (ExpiredJwtException e) {
            exception = e;
            logger.error("token '{}' 已过期！", token);
        }
        throw new JwtException("token 解析异常！", exception);
    }

    public UserEntity parseTokenToUser(String token) {
        @SuppressWarnings("rawtypes")
        Jwt jwt = parseToken(token);
        Claims claims = (Claims) jwt.getBody();
        String username = claims.getSubject();
        String userId = (String) claims.get("userId");
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUsername(username);
        return user;
    }
}
