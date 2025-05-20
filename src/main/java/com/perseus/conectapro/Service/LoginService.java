package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.LoginDTO;
import com.perseus.conectapro.DTO.LoginResponseDTO;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.UsuarioRepository;
import com.perseus.conectapro.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponseDTO RespostaLogin(LoginDTO loginDTO){
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

        int id = usuario.getIdUsuario();

        // Criar o DTO de resposta
        return new LoginResponseDTO(id, uf, tipoUsuario, token);
    }

}
