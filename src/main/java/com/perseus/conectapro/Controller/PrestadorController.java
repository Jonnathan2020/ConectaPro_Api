package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.PrestadorCreateDTO;
import com.perseus.conectapro.DTO.PrestadorUpdateDTO;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.Service.PrestadorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;
    @Autowired
    private PrestadorRepository prestadorRepository;

    //listar prestadores
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public List<Prestador> consultarPrestadores(
            @And({
                    @Spec(path = "idUsuario", spec = Equal.class),
                    @Spec(path = "cpf", spec = Like.class),
                    @Spec(path = "statusDisponibilidade", spec = Equal.class),
                    @Spec(path = "idPlano.idPlano", spec = Equal.class),
                    @Spec(path = "nome", spec = Like.class),
                    @Spec(path = "email", spec = Equal.class),
                    @Spec(path = "telefone", spec = Like.class), // Adicionando filtro para telefone
                    @Spec(path = "tipoUsuario", spec = Equal.class), // Filtro para tipo de usuário
                    @Spec(path = "role", spec = Equal.class),
                    @Spec(path = "endereco.cidade", spec = Like.class), // Filtro para o relacionamento com Endereco, caso queira filtrar por algum atributo do endereço
                    @Spec(path = "endereco.uf", spec = Equal.class),
                    @Spec(path = "endereco.cep", spec = Like.class)
            })Specification<Prestador> spec
            ){

        List<Prestador> prestador = prestadorRepository.findAll(spec);
        if (prestador.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum prestador encontrado com os filtros fornecidos.");
        }
        return prestador;

    }

    //Buscar prestador pelo segmento
    @GetMapping("/segmento/{idSegmento}")
    public List<Prestador> consultarPrestadoresPorSegmento(@PathVariable int idSegmento) {
        return prestadorService.consultarPrestadoresPorSegmento(idSegmento);
    }

    //Buscar prestador por id
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public List<Prestador> consultarPrestadorUnico(@PathVariable int id){
        return prestadorService.consultarPrestadorUnico(id);
    }

    //Buscar prestador por nome
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/nome/{nome}")
    public List<Prestador> getPrestadoresByName(@PathVariable String nome){
        return prestadorService.consultarPrestadorPorNome(nome);
    }

    //Buscar prestador por especialidade
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/especialidade/{especialidade}")
    public List<Prestador> getPrestadoresByEspecialidades(@PathVariable String especialidade) {
        return prestadorService.consultarPrestadorPorEspecialidades(especialidade);
    }

    //Buscar prestador por status disponibilidade
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/status/{status}")
    public List<Prestador> getPrestadoresByStatusDisponibilidade(@PathVariable StatusDisponibilidadeEnum status) {
        return prestadorService.consultarPrestadorPorStatusDisponiblidade(status);
    }


    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("{id}")
    public Prestador alterarPrestador(@RequestBody PrestadorUpdateDTO prestadorUpdateDTO, @PathVariable("id") int id){
            return prestadorService.alterarPrestador(id, prestadorUpdateDTO);
    }

    @PostMapping("/registro")
    public ResponseEntity<Prestador> cadastrarPrestador(@RequestBody @Valid PrestadorCreateDTO prestador){
        Prestador prestadorCriado = prestadorService.cadastrarPrestador(prestador);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestadorCriado);
    }

    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        prestadorService.delete(id);
    }
}
