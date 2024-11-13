package com.optilife.security;

import com.optilife.model.entity.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class AuthenticationFilter extends GenericFilterBean {


    private final TokenService tokenService;

    @Autowired
    public AuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String bearerToken = httpRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);

            Optional<Usuario> usuarioOpt = tokenService.validarToken(token);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();

                Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioOpt.get(),null,new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(req, res);
                return;
            }
        }
        res.getWriter().write("Token inválido o ausente");
        res.setContentType("text/plain");
        res.getWriter().flush();
    }
}