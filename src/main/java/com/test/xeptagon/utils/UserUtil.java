package com.test.xeptagon.utils;

import com.test.xeptagon.enums.UserType;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserUtil {

    public static Optional<UserType> getUsersType(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(grantedAuthority -> UserType.valueOf(grantedAuthority.getAuthority()))
                .findAny();
    }
}
