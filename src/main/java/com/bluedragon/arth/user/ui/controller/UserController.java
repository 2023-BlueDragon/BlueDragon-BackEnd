package com.bluedragon.arth.user.ui.controller;

import com.bluedragon.arth.user.application.UserService;
import com.bluedragon.arth.user.domain.entity.User;
import com.bluedragon.arth.user.ui.dto.request.RegisterExpertRequest;
import com.bluedragon.arth.user.ui.dto.request.RegisterUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "User API")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(description = "User 회원가입")
    @PostMapping
    @ResponseStatus(CREATED)
    public User registerUser(@RequestBody RegisterUserRequest request) {
        return userService.registerUser(request);
    }

    @Operation(description = "Expert 회원가입")
    @PostMapping("/expert")
    @ResponseStatus(CREATED)
    public User registerExpert(@RequestBody RegisterExpertRequest request) {
        System.out.println(request.email());
        return userService.registerExpert(request);
    }

    @Operation(description = "내 정보 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public User getMy() {
        return userService.getMy();
    }

}