package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.SolicitacaoServicoDTO;
import com.perseus.conectapro.DTO.filtro.BuscaSolicitacaoServicoFiltro;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.Repository.SolicitacaoServicoRepository;
import com.perseus.conectapro.specification.BuscaSolicitacaoServicoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscaSolicitacaoServicoService {

    private final SolicitacaoServicoRepository solicitacaoServicoRepository;

    public BuscaSolicitacaoServicoService(SolicitacaoServicoRepository repository) {
        this.solicitacaoServicoRepository = repository;
    }

    public List<SolicitacaoServicoDTO> buscar(BuscaSolicitacaoServicoFiltro filtro) {
        List<SolicitacaoServico> solicitacoes = solicitacaoServicoRepository.findAll(BuscaSolicitacaoServicoSpecification.comFiltro(filtro));
        
        if (solicitacoes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum serviço encontrado");
        }
        
        return solicitacoes.stream()
                .map(SolicitacaoServicoDTO::new)
                .collect(Collectors.toList());
    }
}