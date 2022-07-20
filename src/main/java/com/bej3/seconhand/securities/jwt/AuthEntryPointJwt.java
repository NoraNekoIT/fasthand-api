package com.bej3.seconhand.securities.jwt;

import com.bej3.seconhand.payloads.responses.WebResponse;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint, AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    //401 Unauthorized
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        logger.error("Unauthorized error: {}", authException.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        WebResponse<String,String> body = new WebResponse<>(
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                "kesalahan terjadi di token atau login ",
                ""
        );
        response.getWriter().write(new Gson().toJson(body));
    }

    //403 user has permission, but access to some directories is prohibited
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", accessDeniedException.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        WebResponse<String,String> body = new WebResponse<>(
                HttpStatus.FORBIDDEN.value(),
                "FORBIDDEN",
                "user has permission, but access to some directories is prohibited ",
                ""
        );
        response.getWriter().write(new Gson().toJson(body));
    }
}
