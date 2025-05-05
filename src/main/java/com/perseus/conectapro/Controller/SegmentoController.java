package com.perseus.conectapro.Controller;

import com.perseus.conectapro.DTO.SegmentoCreateDTO;
import com.perseus.conectapro.DTO.SegmentoUpdateDTO;
import com.perseus.conectapro.Entity.Segmento;
import com.perseus.conectapro.Service.SegmentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/segmento")
public class SegmentoController {

    @Autowired
    private SegmentoService segmentoService;

    @GetMapping
    public List<Segmento> listarSegmentos() {
        return segmentoService.listarSegmentos();
    }

    @GetMapping("/{id}")
    public Segmento buscarSegmentoPorId(@PathVariable("id") int id) {
        return segmentoService.buscarSegmentoPorId(id);
    }

    @GetMapping("/{desc}")
    public Segmento buscarSegmentoPorNome(@PathVariable String desc){
        return segmentoService.buscarSegmentoPorNome(desc);
    }

    @PostMapping("/registro")
    public ResponseEntity<Segmento> cadastrarSegmento(@RequestBody SegmentoCreateDTO dto) {
        Segmento segmentoCriado = segmentoService.cadastrarSegmento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(segmentoCriado);
    }

    @PutMapping("{id}")
    public ResponseEntity<Segmento> alterarSegmento(@PathVariable("id") int id,@RequestBody SegmentoUpdateDTO segmentoUpdateDTO) {
        Segmento segmentoAtualizado = segmentoService.alterarSegmento(id, segmentoUpdateDTO);
        return ResponseEntity.ok(segmentoAtualizado);
    }

    @DeleteMapping
    public void delete(int id) {
        segmentoService.deletarSegmento(id);
    }

}
