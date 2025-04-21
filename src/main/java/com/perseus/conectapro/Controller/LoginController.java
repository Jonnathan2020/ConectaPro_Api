package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.LoginDTO;
import com.perseus.conectapro.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDTO loginDTO){

        var usuarioAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        authenticationManager.authenticate(usuarioAuthenticationToken);

        return tokenService.obterToken(loginDTO);
    }
}
