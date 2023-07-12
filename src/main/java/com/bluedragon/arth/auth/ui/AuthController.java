package com.bluedragon.arth.auth.ui;

import com.bluedragon.arth.auth.application.AuthService;
import com.bluedragon.arth.auth.ui.dto.request.LoginRequest;
import com.bluedragon.arth.auth.ui.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Auth API")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(description = "로그인")
    @PostMapping("/login")
    @ResponseStatus(OK)
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Operation(description = "refresh")
    @PostMapping("/refresh")
    @ResponseStatus(OK)
    public String refresh(final @RequestHeader("Refresh-Token") String refreshToken) {
        return authService.refresh(refreshToken);
    }

}