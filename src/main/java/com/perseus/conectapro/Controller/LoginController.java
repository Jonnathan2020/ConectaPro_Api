package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.LoginDTO;
import com.perseus.conectapro.DTO.LoginResponseDTO;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.UsuarioRepository;
import com.perseus.conectapro.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){

        try{
            var usuarioAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
            authenticationManager.authenticate(usuarioAuthenticationToken);

            // Recupera o usuário para pegar os dados adicionais
            Usuario usuario = usuarioRepository.findByEmail(loginDTO.email());

            // Gerar o token
            String token = tokenService.obterToken(loginDTO);

            // A partir do usuário, recuperar o id, uf e tipo de usuário
            String uf = usuario.getEndereco() != null && usuario.getEndereco().getUf() != null
                    ? usuario.getEndereco().getUf().name()  // Convertendo de UfEnum para String
                    : null;

            String tipoUsuario = usuario.getTipoUsuario().name();
            String nome = usuario.getNome();

            int id = usuario.getIdUsuario();

            // Criar DTO de resposta
            LoginResponseDTO response = new LoginResponseDTO(id, uf, tipoUsuario, nome, token);

            return ResponseEntity.ok(response);
        }
         catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos.");
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Falha na autenticação. Verifique suas credenciais.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor: " + ex.getMessage());
        }

    }
}


