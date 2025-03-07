package com.perseus.conectapro.Controller;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //Buscar prestador por habilidade
    @GetMapping("/habilidade/{habilidade}")
    public List<Prestador> getPrestadoresByHabilidade(@PathVariable String habilidade) {
        return prestadorService.consultarPrestadorPorHabilidade(habilidade);
    }

    //Buscar prestador por especialidade
    @GetMapping("/especialidade/{especialidade}")
    public List<Prestador> getPrestadoresByEspecialidade(@PathVariable String especialidade) {
        return prestadorService.consultarPrestadorPorEspecialidade(especialidade);
    }

    @PutMapping
    public Prestador alterarPrestador(@RequestBody Prestador prestador, @PathVariable("id") int id){
        if (id == prestador.getIdUsuario()){
            return prestadorService.alterarPrestador(prestador.getIdUsuario());
        }
        else
            return null;
    }

    @PostMapping("/Registro")
    public Prestador cadastrarPrestador(@RequestBody Prestador prestador){
        return prestadorService.cadastrarPrestador(prestador);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        prestadorService.delete(id);
    }
}
