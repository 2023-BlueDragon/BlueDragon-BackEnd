package com.bluedragon.arth.user.application;

import com.bluedragon.arth.user.domain.entity.User;
import com.bluedragon.arth.z_infrastructure.global.security.utils.UserSecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserSecurityUtils userSecurityUtils;

    public User getCurrentUser() {
        return userSecurityUtils.getCurrentUser();
    }

}