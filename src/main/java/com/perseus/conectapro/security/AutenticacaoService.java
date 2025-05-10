package com.perseus.conectapro.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.perseus.conectapro.DTO.LoginDTO;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class AutenticacaoService implements TokenService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }



    public String gerarTokenJwt(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret-key-api");

            return JWT.create()
                    .withIssuer("ConectaPro_Api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar o token!!" + exception.getMessage());
        }
    }

    @Override
    public String obterToken(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.email());

        return gerarTokenJwt(usuario);
    }

    public String validarTokenJwt(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret-key-api");

            return JWT.require(algorithm)
                    .withIssuer("ConectaPro_Api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
                return "";
            }
    }

    private Instant geraDataExpiracao() {
        return null;
    }
}
