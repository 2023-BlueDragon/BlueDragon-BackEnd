package com.bluedragon.arth.z_infrastructure.global.security.utils;

import com.bluedragon.arth.user.domain.entity.User;
import com.bluedragon.arth.z_infrastructure.global.security.principle.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurityUtils {

    public User getCurrentUser() {
        CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return details.user();
    }

}