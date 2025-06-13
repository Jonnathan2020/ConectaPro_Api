package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.AvaliacaoDTO;
import com.perseus.conectapro.DTO.ServicoCreateDTO;
import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.DTO.ServicoPropostaDiretaDTO;
import com.perseus.conectapro.DTO.ServicoUpdateDTO;
import com.perseus.conectapro.Service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // Cadastar servico
    @PostMapping("/registro")
    public ResponseEntity<ServicoDTO> cadastrarServico(@RequestBody @Valid ServicoCreateDTO servicoDTO) {
        ServicoDTO servicoCriado = servicoService.cadastrarServico(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoCriado);
    }

    // consultar servicos
    @GetMapping
    public List<ServicoDTO> consultarServicos() {
        return servicoService.consultarServicos();
    }

    @GetMapping("/{id}")
    public ServicoDTO consultarServicoPorId(@PathVariable int id) {
        return servicoService.consultarServicoPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> alterarServico(@PathVariable("id") int id,
            @RequestBody ServicoUpdateDTO servicoUpdateDTO) {
        ServicoDTO servicoAtualizado = servicoService.alterarServico(id, servicoUpdateDTO);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        servicoService.delete(id);
    }

    @PostMapping("/candidatar")
    public ResponseEntity<ServicoDTO> criarServicoPorCandidatura(@RequestBody ServicoCreateDTO dto) {
        ServicoDTO servicoCriado = servicoService.gerarServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoCriado);
    }

    @PostMapping("/proposta-direta")
    public ResponseEntity<ServicoDTO> criarPropostaDireta(@RequestBody ServicoPropostaDiretaDTO dto) {
        ServicoDTO criado = servicoService.criarServicoPorPropostaDireta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<ServicoDTO> aprovarOrcamentoServico(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.aprovarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<ServicoDTO> recusarOrcamentoServico(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.recusarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<ServicoDTO> pagamentoServico(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.pagarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<ServicoDTO> iniciarServico(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.iniciarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ServicoDTO> finalizarServico(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.finalizarServico(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<ServicoDTO> confirmarFinalizacao(@PathVariable int id) {
        ServicoDTO servicoDTO = servicoService.confirmarFinalizacao(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PutMapping("/{id}/aceitar")
    public ResponseEntity<ServicoDTO> aceitarProposta(@PathVariable int id) {
        return ResponseEntity.ok(servicoService.aceitarProposta(id));
    }

    @PutMapping("/{id}/rejeitar")
    public ResponseEntity<ServicoDTO> recusarProposta(@PathVariable int id) {
        return ResponseEntity.ok(servicoService.recusarProposta(id));
    }

    @PutMapping("/{id}/avaliar")
    public ResponseEntity<ServicoDTO> avaliarServico(@PathVariable int id, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        return ResponseEntity.ok(servicoService.avaliarServico(id, avaliacaoDTO.getNvlSatisfacao()));
    }

}
