package com.github.cloudgyb.heartchat.config;

import com.github.cloudgyb.heartchat.domain.UserEntity;
import com.github.cloudgyb.heartchat.modules.common.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author geng
 * @since 2023/03/04 19:31:45
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final static String bearerAuthHeaderValuePrefix = "Bearer ";
    private final Set<String> ignoreUrls;
    private final JwtService jwtService;

    public JwtRequestFilter(JwtConfig jwtConfig, JwtService jwtService) {
        this.ignoreUrls = new HashSet<>(jwtConfig.getSkipValidateUrls());
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        String requestURI = request.getRequestURI();
        if (ignoreUrls.contains(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(bearerAuthHeaderValuePrefix)) {
            respUnauthorized(response);
            return;
        } else {
            String token = authorizationHeader.substring(bearerAuthHeaderValuePrefix.length());
            try {
                UserEntity user = jwtService.parseTokenToUser(token);
                request.setAttribute("_user", user);
            } catch (JwtException e) {
                logger.error(e.getCause().getMessage());
                respUnauthorized(response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void respUnauthorized(HttpServletResponse response) throws IOException {
        response.setHeader(HttpHeaders.AUTHORIZATION, bearerAuthHeaderValuePrefix.trim());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.flushBuffer();
    }
}
