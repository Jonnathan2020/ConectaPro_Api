package com.perseus.conectapro.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.DTO.SolicitacaoServicoDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import com.perseus.conectapro.Entity.Enuns.StatusServicoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import com.perseus.conectapro.Repository.ServicoRepository;
import com.perseus.conectapro.Repository.SolicitacaoServicoRepository;

@Service
public class PerfilEmpresaClienteService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private SolicitacaoServicoRepository solicitacaoServicoRepository;
	
    public List<SolicitacaoServicoDTO> buscarSolicitacoesDaEmpresa(int idEmpresa) {
        List<SolicitacaoServico> solicitacoes = solicitacaoServicoRepository
        		.findByIdEmpresaClienteIdUsuarioAndIdPrestadorIsNullAndStatusSolicitacaoIn(
        				idEmpresa, 
        				List.of(StatusSolicitacaoEnum.ATIVA, StatusSolicitacaoEnum.INATIVA));

        if(solicitacoes.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma solicitação publicada");
        }
        
        return solicitacoes.stream()
        		.map(solicitacao -> new SolicitacaoServicoDTO(solicitacao))
                .collect(Collectors.toList());
    }
    
    public List<ServicoDTO> buscarPropostas(Integer idEmpresa) {
    	List<Servico> servicos = servicoRepository
    			.findByIdEmpresaClienteIdUsuarioAndIdPrestadorIsNotNullAndStatusServico(idEmpresa,
    					StatusServicoEnum.ORCAMENTO);
    	
    	if(servicos.isEmpty()) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma proposta recebida");
    	}
    	
    	return servicos.stream()
    			.map(ServicoDTO::new)
                .collect(Collectors.toList());
    	
    }
    
    public List<ServicoDTO> buscarHistorico(Integer idEmpresa) {

		List<StatusServicoEnum> statusExcluidos = List.of(StatusServicoEnum.ORCAMENTO, StatusServicoEnum.RECUSADO);

		List<Servico> servicos = servicoRepository
				.findByIdEmpresaClienteIdUsuarioAndStatusServicoNotIn(idEmpresa, statusExcluidos);


    	if(servicos.isEmpty()) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum serviço finalizado");
    	}
    	
    	return servicos.stream()
    			.map(ServicoDTO::new)
    			.collect(Collectors.toList());
    }

}
