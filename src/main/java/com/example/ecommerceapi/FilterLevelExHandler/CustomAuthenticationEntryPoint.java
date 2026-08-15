package com.example.ecommerceapi.FilterLevelExHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper obj;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String,Object> errorDetails = new HashMap<>();
        errorDetails.put("Status",401);
        errorDetails.put("Error","UNAUTHENICATED");
        errorDetails.put("Message",authException.getMessage());
        errorDetails.put("note","poda thevdya PUNDA");
        errorDetails.put("path",request.getPathInfo());

        obj.writeValue(response.getOutputStream(),errorDetails);
    }
}
