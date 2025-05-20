package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.LoginDTO;
import com.perseus.conectapro.DTO.LoginResponseDTO;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.UsuarioRepository;
import com.perseus.conectapro.Service.LoginService;
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
    private LoginService loginService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO){
        return loginService.RespostaLogin(loginDTO);
    }
}


