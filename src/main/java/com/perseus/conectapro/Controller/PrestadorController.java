package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.PrestadorCreateDTO;
import com.perseus.conectapro.DTO.PrestadorDTO;
import com.perseus.conectapro.DTO.PrestadorUpdateDTO;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Service.PrestadorService;
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

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    //listar prestadores
    @GetMapping
    public List<PrestadorDTO> consultarPrestadores(
            @And({
                    @Spec(path = "idUsuario", spec = Equal.class),
                    @Spec(path = "cpf", spec = Like.class),
                    @Spec(path = "statusDisponibilidade", spec = Equal.class),
                    @Spec(path = "idPlano.idPlano", spec = Equal.class)
            })Specification<Prestador> spec
            ){
        return prestadorService.consultarPrestadores(spec);
    }

    //Buscar prestador por id
    @GetMapping("/{id}")
    public PrestadorDTO consultarPrestadorUnico(@PathVariable int id){
        return prestadorService.consultarPrestadorUnico(id);
    }

    //Buscar prestador por nome
    @GetMapping("/nome/{nome}")
    public List<PrestadorDTO> consultarPrestadoresPorNome(@PathVariable String nome){
        return prestadorService.consultarPrestadorPorNome(nome);
    }

    //Buscar prestador por especialidade
    @GetMapping("/especialidade/{especialidade}")
    public List<PrestadorDTO> getPrestadoresByEspecialidades(@PathVariable String especialidade) {
        return prestadorService.consultarPrestadorPorEspecialidades(especialidade);
    }

    //Buscar prestador por status disponibilidade
    @GetMapping("/status/{status}")
    public List<PrestadorDTO> getPrestadoresByStatusDisponibilidade(@PathVariable StatusDisponibilidadeEnum status) {
        return prestadorService.consultarPrestadorPorStatusDisponiblidade(status);
    }


    @PutMapping("{id}")
    public PrestadorDTO alterarPrestador(@RequestBody PrestadorUpdateDTO prestadorUpdateDTO, @PathVariable("id") int id){
            return prestadorService.alterarPrestador(id, prestadorUpdateDTO);
    }

    @PostMapping("/registro")
    public ResponseEntity<Prestador> cadastrarPrestador(@RequestBody @Valid PrestadorCreateDTO prestador){
        Prestador prestadorCriado = prestadorService.cadastrarPrestador(prestador);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestadorCriado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        prestadorService.delete(id);
    }
}
