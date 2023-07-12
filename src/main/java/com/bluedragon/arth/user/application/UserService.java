package com.bluedragon.arth.user.application;

import com.bluedragon.arth.user.domain.enums.UserRole;
import com.bluedragon.arth.user.domain.entity.User;
import com.bluedragon.arth.user.domain.repository.UserJpaRepository;
import com.bluedragon.arth.user.ui.dto.request.RegisterExpertRequest;
import com.bluedragon.arth.user.ui.dto.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public User registerUser(RegisterUserRequest request) {
        return userJpaRepository.save(User.builder()
                .email(request.email())
                .passWord(passwordEncoder.encode(request.passWord()))
                .nickName(request.nickName())
                .userRole(UserRole.USER)
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public User registerExpert(RegisterExpertRequest request) {
        return userJpaRepository.save(User.builder()
                .email(request.email())
                .passWord(passwordEncoder.encode(request.passWord()))
                .nickName(request.nickName())
                .major(request.major())
                .userRole(UserRole.EXPERT)
                .build());
    }

    @Transactional(readOnly = true)
    public User getMy() {
        return userFacade.getCurrentUser();
    }

}