package com.example.ecommerceapi.custumFilter;

import com.example.ecommerceapi.FilterLevelExHandler.CustomAuthenticationEntryPoint;
import com.example.ecommerceapi.config.SecurityConfig;
import com.example.ecommerceapi.modal.User;
import com.example.ecommerceapi.service.AuthService;
import com.example.ecommerceapi.service.jwtService;
import com.example.ecommerceapi.service.kuttyService;
import com.example.ecommerceapi.service.userService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    jwtService service;

    @Autowired
    AuthService auth;

    @Autowired
    CustomAuthenticationEntryPoint custom;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
                username = service.getUsernameByToken(token);
                UserDetails user = auth.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(tok);

            }
        } catch (Exception e) {
            BadCredentialsException bd = new BadCredentialsException("UNAUTHENTICATED");
            custom.commence(request,response,bd);
        }
        filterChain.doFilter(request, response);

    }
}
