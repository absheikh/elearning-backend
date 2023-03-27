package com.example.rest_api_jwt.Security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.PARAMETER})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal

public @interface CurrentUser {
}
