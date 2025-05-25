package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.PrestadorBuscaDTO;
import com.perseus.conectapro.DTO.PrestadorDTO;
import com.perseus.conectapro.DTO.filtro.BuscaPrestadorFiltro;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.specification.BuscaPrestadorSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaPrestadorService {

    private final PrestadorRepository repository;

    public BuscaPrestadorService(PrestadorRepository repository) {
        this.repository = repository;
    }

    public List<PrestadorBuscaDTO> buscar(BuscaPrestadorFiltro filtro) {
        return repository.findAll(BuscaPrestadorSpecification.comFiltro(filtro))
                .stream()
                .map(PrestadorBuscaDTO::new)
                .toList();
    }
}