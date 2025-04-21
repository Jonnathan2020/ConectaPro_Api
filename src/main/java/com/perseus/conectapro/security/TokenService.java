package com.perseus.conectapro.security;

import com.perseus.conectapro.DTO.LoginDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TokenService extends UserDetailsService {

    public String obterToken(LoginDTO loginDTO);
    public String validarTokenJwt(String token);
}
