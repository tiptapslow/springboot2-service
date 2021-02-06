package com.kimsk.springboot.config.auth.dto;

import com.kimsk.springboot.domain.user.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        name = user.getName();
        email = user.getEmail();
        picture = user.getPicture();
    }
}
