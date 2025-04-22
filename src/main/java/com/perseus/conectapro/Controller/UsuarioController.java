package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.UsuarioCreateDTO;
import com.perseus.conectapro.DTO.UsuarioUpdateDTO;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.UsuarioRepository;
import com.perseus.conectapro.Service.UsuarioService;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Usuario admin padrao Js: "email": "jonnathan.santos15@gmail.com",
    //                         "senha": "senhaforte123"

    //Rota somente para admin
    @GetMapping("/admin")
    private String getAdmin(){
        return "Permissão de administrador";
    }

    //Rota somente para usuario
    @GetMapping("/user")
    private String getUser(){
        return "Permissão de usuário";
    }


    //Listar usuario
    @GetMapping
    public List<Usuario> listarUsuario(
            @And({
                    @Spec(path = "idUsuario", spec = Equal.class),
                    @Spec(path = "nome", spec = Like.class),
                    @Spec(path = "email", spec = Equal.class),
                    @Spec(path = "telefone", spec = Like.class), // Adicionando filtro para telefone
                    @Spec(path = "tipoUsuario", spec = Equal.class), // Filtro para tipo de usuário
                    @Spec(path = "role", spec = Equal.class),
                    @Spec(path = "endereco.cidade", spec = Like.class), // Filtro para o relacionamento com Endereco, caso queira filtrar por algum atributo do endereço
                    @Spec(path = "endereco.uf", spec = Equal.class),
                    @Spec(path = "endereco.cep", spec = Like.class)

            }) Specification<Usuario> spec
    ){
        List<Usuario> usuarios = usuarioRepository.findAll(spec);
        if (usuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado com os filtros fornecidos.");
        }
        return usuarios;
    }

    //Buscar usuário por id
    @GetMapping("/{id}")
    public Usuario consultarUsuarioPorId(@PathVariable int id){
        return usuarioService.consultarUsuarioPorId(id);
    }

    //Buscar usuário pelo nome
    @GetMapping("/nome/{nome}")
    public List<Usuario> getUsuarioByName(@PathVariable String nome){
        return usuarioService.consultarUsuarioPorNome(nome);
    }

    //Alterar usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarUsuario(@RequestBody UsuarioUpdateDTO usuarioUpdateDTO, @PathVariable("id") int id){
        Usuario usuarioAtualizado = usuarioService.alterarUsuario(id, usuarioUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
    }

    //Cadastrar usuário
    @PostMapping("/registro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuarioCriado = usuarioService.cadastrarUsuario(usuarioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        usuarioService.delete(id);
    }

}
