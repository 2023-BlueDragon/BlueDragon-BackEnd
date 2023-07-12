package com.bluedragon.arth.z_infrastructure.global.jwt.utils;

import com.bluedragon.arth.auth.domain.exception.WrongTokenTypeException;
import com.bluedragon.arth.z_infrastructure.global.jwt.enums.JwtType;
import com.bluedragon.arth.z_infrastructure.global.jwt.properties.JwtProperties;
import com.bluedragon.arth.z_infrastructure.global.security.principle.CustomUserDetails;
import com.bluedragon.arth.user.domain.entity.User;
import com.bluedragon.arth.user.domain.repository.UserJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtParser {

    private final UserJpaRepository userJpaRepository;
    private final JwtProperties jwtProperties;

    public Jws<Claims> getClaims(final String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getAccessKey()).parseClaimsJws(token);
    }

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaims(token);

        if(isWrongType(claims, JwtType.ACCESS)) {
            throw WrongTokenTypeException.EXCEPTION;
        }

        final User user = userJpaRepository.findById(claims.getBody().getSubject())
                .orElseThrow();

        final CustomUserDetails details = new CustomUserDetails(user);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String extractTokenFromRequest(final HttpServletRequest request) {
        return extractToken(request.getHeader("Authorization"));
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }

}