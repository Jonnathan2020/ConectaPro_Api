package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.PrestadorBuscaDTO;
import com.perseus.conectapro.DTO.PrestadorDTO;
import com.perseus.conectapro.DTO.filtro.BuscaPrestadorFiltro;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.specification.BuscaPrestadorSpecification;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BuscaPrestadorService {

    private final PrestadorRepository repository;

    public BuscaPrestadorService(PrestadorRepository repository) {
        this.repository = repository;
    }

    public List<PrestadorBuscaDTO> buscar(BuscaPrestadorFiltro filtro) {

        var resultados = repository.findAll(BuscaPrestadorSpecification.comFiltro(filtro))
                .stream()
                .map(PrestadorBuscaDTO::new)
                .toList();

        if (resultados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum prestador encontrado.");
        }

        return resultados;
        
    }
}