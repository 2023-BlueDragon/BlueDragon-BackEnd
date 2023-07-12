package com.bluedragon.arth.user.application;

import com.bluedragon.arth.z_global.security.principle.CustomUserDetails;
import com.bluedragon.arth.user.domain.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public User getCurrentUser() {
        CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return details.user();
    }

}