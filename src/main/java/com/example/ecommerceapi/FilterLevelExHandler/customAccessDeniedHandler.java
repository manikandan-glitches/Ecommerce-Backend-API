package com.example.ecommerceapi.FilterLevelExHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class customAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper obj;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        Map<String,Object> errorDetails = new HashMap<>();
        errorDetails.put("Status",403);
        errorDetails.put("Error","Access Denied");
        errorDetails.put("Message",accessDeniedException.getMessage());
        errorDetails.put("note","poda thevdya");
        errorDetails.put("path",request.getPathInfo());

        obj.writeValue(response.getOutputStream(),errorDetails);
    }

}