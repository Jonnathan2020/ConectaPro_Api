package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.SegmentoCreateDTO;
import com.perseus.conectapro.Entity.Segmento;
import com.perseus.conectapro.Repository.SegmentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SegmentoService {
    @Autowired
    private SegmentoRepository segmentoRepository;

    //listar segmento
    public List<Segmento> listarSegmentos() {
        return segmentoRepository.findAll();
    }

    //buscar por id
    public Segmento buscarSegmentoPorId(int id) {
        return segmentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Segmento não encontrado!"));
    }

    private List<Segmento> buscarSegmentosPorIds(List<Integer> ids) {
        return ids.stream()
                .map(id -> segmentoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Segmento com ID " + id + " não encontrado")))
                .collect(Collectors.toList());
    }

    //cadastrar segmento
    public Segmento cadastrarSegmento(SegmentoCreateDTO dto) {
        Segmento segmento = new Segmento();
        segmento.setDescSegmento(dto.getDesc());
        return segmentoRepository.save(segmento);
    }

    //deletar segmento
    public void deletarSegmento(int id) { segmentoRepository.deleteById(id); }

}
