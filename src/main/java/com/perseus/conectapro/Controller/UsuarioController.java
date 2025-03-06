package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Listar usuario
    @GetMapping
    public List<Usuario> listarUsuario(){
        return usuarioService.consultarUsuarios();
    }

    @GetMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

    @PutMapping
    public Usuario alterarUsuario(@RequestBody Usuario usuario, @PathVariable("id") int id){
        if(id == usuario.getIdUsuario()){
            return usuarioService.alterarUsuario(usuario.getIdUsuario());
        }
        else
            return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        usuarioService.delete(id);
    }

}
