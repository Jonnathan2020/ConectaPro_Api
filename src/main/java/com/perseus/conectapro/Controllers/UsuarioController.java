package com.perseus.conectapro.Controllers;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario") // Normalmente usamos letras minúsculas em URLs
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Cadastro de Cliente (EmpresaCliente)
    @PostMapping("/cadastro-cliente")
    public ResponseEntity<EmpresaCliente> cadastrarEmpresa(@RequestBody EmpresaCliente empresaCliente) {
        EmpresaCliente clienteSalvo = usuarioService.cadastrarCliente(empresaCliente);
        return ResponseEntity.ok(clienteSalvo);
    }
}
