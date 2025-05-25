package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.SolicitacaoServicoDTO;
import com.perseus.conectapro.DTO.filtro.BuscaSolicitacaoServicoFiltro;
import com.perseus.conectapro.Repository.SolicitacaoServicoRepository;
import com.perseus.conectapro.specification.BuscaSolicitacaoServicoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscaSolicitacaoServicoService {

    @Autowired
    private SolicitacaoServicoRepository solicitacaoServicoRepository;

    public List<SolicitacaoServicoDTO> buscar(BuscaSolicitacaoServicoFiltro filtro) {
        return solicitacaoServicoRepository.findAll(BuscaSolicitacaoServicoSpecification.comFiltro(filtro))
                .stream()
                .map(SolicitacaoServicoDTO::new)
                .collect(Collectors.toList());
    }
}