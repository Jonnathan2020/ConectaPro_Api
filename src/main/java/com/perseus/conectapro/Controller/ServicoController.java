package com.perseus.conectapro.Controller;

import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.perseus.conectapro.DTO.ServicoCreateDTO;
import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.DTO.ServicoUpdateDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Repository.ServicoRepository;
import com.perseus.conectapro.Service.ServicoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    @PostMapping("/candidatar")
    public ResponseEntity<ServicoDTO> criarServicoPorCandidatura(@RequestBody ServicoCreateDTO dto) {
        ServicoDTO servicoCriado = servicoService.gerarServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoCriado);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<ServicoDTO> aprovarOrcamentoServico(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.aprovarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<ServicoDTO> recusarOrcamentoServico(@PathVariable int id){
        ServicoDTO servicoDTO = servicoService.recusarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<ServicoDTO> pagamentoServico(@PathVariable int id){
        ServicoDTO servicoDTO = servicoService.pagarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<ServicoDTO> iniciarServico(@PathVariable int id){
        ServicoDTO servicoDTO = servicoService.iniciarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ServicoDTO> finalizarServico(@PathVariable int id){
        ServicoDTO servicoDTO = servicoService.finalizarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<ServicoDTO> confirmarFinalizacao(@PathVariable int id){
        ServicoDTO servicoDTO = servicoService.confirmarFinalizacao(id);
        return ResponseEntity.ok(servicoDTO);
    }
}

