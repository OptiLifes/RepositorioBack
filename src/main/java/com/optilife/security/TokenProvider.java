package com.optilife.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    public Authentication getAuthentication(String token) {

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        User principal = new User(claims)
    }

    private String jwtSecret;
}
