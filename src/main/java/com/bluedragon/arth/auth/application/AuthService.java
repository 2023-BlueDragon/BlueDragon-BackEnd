package com.bluedragon.arth.auth.application;

import com.bluedragon.arth.auth.domain.exception.WrongPassWordException;
import com.bluedragon.arth.auth.domain.exception.WrongTokenTypeException;
import com.bluedragon.arth.auth.ui.dto.request.LoginRequest;
import com.bluedragon.arth.auth.ui.dto.response.TokenResponse;
import com.bluedragon.arth.user.domain.enums.UserRole;
import com.bluedragon.arth.z_global.jwt.enums.JwtType;
import com.bluedragon.arth.z_global.jwt.utils.JwtParser;
import com.bluedragon.arth.z_global.jwt.utils.JwtProvider;
import com.bluedragon.arth.user.domain.entity.User;
import com.bluedragon.arth.user.domain.repository.UserJpaRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final JwtProvider jwtProvider;
    private final JwtParser jwtParser;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        final User user = userJpaRepository.findById(request.id())
                .orElseThrow();

        checkPassWord(user.getPassWord(), request.passWord());

        return jwtProvider.generateTokens(user.getEmail(), user.getUserRole());
    }

    public String refresh(final String refreshToken) {
        Jws<Claims> claims = jwtParser.getClaims(jwtParser.extractToken(refreshToken));

        if(jwtParser.isWrongType(claims, JwtType.REFRESH)) {
            throw WrongTokenTypeException.EXCEPTION;
        }

        return reissue(claims);
    }

    private void checkPassWord(final String original, final String expected) {
        if(!passwordEncoder.matches(expected, original)) {
            throw WrongPassWordException.EXCEPTION;
        }
    }

    private String reissue(final Jws<Claims> claims) {
        return jwtProvider.generateAccessToken(claims.getBody().getSubject(),
                (UserRole) claims.getHeader().get("authority"));
    }

}