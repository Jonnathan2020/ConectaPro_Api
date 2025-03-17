package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Buscar usuário por email
    @GetMapping("/{id}")
    public Usuario consultarUsuarioPorId(@PathVariable int id){
        return usuarioService.consultarUsuarioPorId(id);
    }

    @GetMapping("/nome/{nome}")
    public List<Usuario> getUsuarioByName(@PathVariable String nome){
        return usuarioService.consultarUsuarioPorNome(nome);
    }

    @PutMapping("/{id}")
    public Usuario alterarUsuario(@RequestBody Usuario usuario, @PathVariable("id") int id){
        if(id == usuario.getIdUsuario()){
            return usuarioService.alterarUsuario(usuario.getIdUsuario());
        }
        else
            return null;
    }

    @PostMapping("/Registro")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        usuarioService.delete(id);
    }

}
