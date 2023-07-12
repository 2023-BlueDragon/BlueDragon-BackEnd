package com.bluedragon.arth.z_infrastructure.global.jwt.filter;

import com.bluedragon.arth.z_common.error.ErrorCode;
import com.bluedragon.arth.z_infrastructure.global.error.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, ErrorCode.EXPIRED_JWT);
        } catch (MalformedJwtException e) {
            setErrorResponse(response, ErrorCode.MALFORMED_JWT);
        } catch (UnsupportedJwtException e) {
            setErrorResponse(response, ErrorCode.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e) {
            setErrorResponse(response, ErrorCode.ILLEGAL_ARGUMENT_ERROR);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
        try {
            responseToClient(response, getErrorResponse(errorCode));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.status());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    private ErrorResponse getErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.create(errorCode);
    }

}