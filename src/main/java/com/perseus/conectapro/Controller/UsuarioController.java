package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.UsuarioCreateDTO;
import com.perseus.conectapro.DTO.UsuarioUpdateDTO;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Listar usuario
    @GetMapping
    public List<Usuario> listarUsuario(){
        return usuarioService.consultarUsuarios();
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
