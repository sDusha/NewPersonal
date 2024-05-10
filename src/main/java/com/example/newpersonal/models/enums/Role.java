package com.example.newpersonal.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER ("Пользователь"),
    ROLE_ADMIN ("Админ"),
    ROLE_OWNER ("Владелец");

    private final String russianTranslation;

    Role(String russianTranslation) {
        this.russianTranslation = russianTranslation;
    }

    public String getRussianTranslation() {
        return russianTranslation;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
