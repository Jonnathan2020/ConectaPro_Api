package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.PrestadorCreateDTO;
import com.perseus.conectapro.DTO.PrestadorUpdateDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Service.PrestadorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    //listar prestadores
    @GetMapping
    public List<Prestador> consultarPrestadores(){
        return prestadorService.consultarPrestadores();
    }

    //Buscar prestador por id
    @GetMapping("/{id}")
    public Prestador consultarPrestador(@PathVariable int id){
        return prestadorService.consultarPrestadorUnico(id);
    }

    //Buscar prestador por nome
    @GetMapping("/nome/{nome}")
    public List<Prestador> getPrestadoresByName(@PathVariable String nome){
        return prestadorService.consultarPrestadorPorNome(nome);
    }

    //Buscar prestador por especialidade
    @GetMapping("/especialidade/{especialidade}")
    public List<Prestador> getPrestadoresByEspecialidade(@PathVariable String especialidade) {
        return prestadorService.consultarPrestadorPorEspecialidade(especialidade);
    }

    @PutMapping("{id}")
    public Prestador alterarPrestador(@RequestBody PrestadorUpdateDTO prestadorUpdateDTO, @PathVariable("id") int id){
            return prestadorService.alterarPrestador(id, prestadorUpdateDTO);
    }

    @PostMapping("/registro")
    public ResponseEntity<Prestador> cadastrarPrestador(@RequestBody PrestadorCreateDTO prestador){
        Prestador prestadorCriado = prestadorService.cadastrarPrestador(prestador);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestadorCriado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        prestadorService.delete(id);
    }
}
