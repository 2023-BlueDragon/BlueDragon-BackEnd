package com.bluedragon.arth.z_global.jwt.filter;

import com.bluedragon.arth.z_global.jwt.utils.JwtParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtParser jwtParser;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
        final String token = jwtParser.extractTokenFromRequest(request);

        if(token != null) {
            SecurityContextHolder.getContext().setAuthentication(jwtParser.getAuthentication(token));
        }

        chain.doFilter(request, response);
    }

}