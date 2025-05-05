package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.ServicoCreateDTO;
import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.DTO.ServicoUpdateDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Repository.ServicoRepository;
import com.perseus.conectapro.Service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ServicoRepository servicoRepository;

    //Cadastar servico
    @PostMapping("/registro")
    public ResponseEntity<ServicoDTO> cadastrarServico(@RequestBody @Valid ServicoCreateDTO servicoDTO) {
        ServicoDTO servicoCriado = servicoService.cadastrarServico(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoCriado);
    }

    //consultar servicos
    @GetMapping
    public List<ServicoDTO> consultarServicos(){
        return servicoService.consultarServicos();
    }

    @GetMapping("/{id}")
    public ServicoDTO consultarServicoPorId(@PathVariable int id){
        return servicoService.consultarServicoPorId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Servico> alterarServico(@PathVariable("id") int id,@RequestBody ServicoUpdateDTO servicoUpdateDTO){
            Servico servicoAtualizado = servicoService.alterarServico(id, servicoUpdateDTO);
            return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        servicoService.delete(id);
    }

}

