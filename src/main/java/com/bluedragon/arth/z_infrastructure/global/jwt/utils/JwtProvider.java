package com.bluedragon.arth.z_infrastructure.global.jwt.utils;

import com.bluedragon.arth.auth.ui.dto.response.TokenResponse;
import com.bluedragon.arth.user.domain.enums.UserRole;
import com.bluedragon.arth.z_infrastructure.global.jwt.enums.JwtType;
import com.bluedragon.arth.z_infrastructure.global.jwt.properties.JwtProperties;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(final String id, final UserRole userRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(id)
                .claim("authority", userRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExpire()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getAccessKey())
                .compact();
    }

    public String generateRefreshToken(final String id, final UserRole userRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(id)
                .claim("authority", userRole)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpire()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

    public TokenResponse generateTokens(final String id, final UserRole userRole) {
        return new TokenResponse(
                generateAccessToken(id, userRole),
                generateRefreshToken(id, userRole));
    }

}