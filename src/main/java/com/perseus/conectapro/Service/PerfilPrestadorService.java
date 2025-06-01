package com.perseus.conectapro.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.perseus.conectapro.DTO.ServicoDTO;
import com.perseus.conectapro.Entity.Servico;
import com.perseus.conectapro.Entity.Enuns.StatusServicoEnum;
import com.perseus.conectapro.Repository.ServicoRepository;

@Service
public class PerfilPrestadorService {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	public List<ServicoDTO> buscarServicosPrestados(int idPrestador) {
        List<Servico> servicos =  servicoRepository.findByIdPrestadorIdUsuarioAndStatusServicoIn(
                idPrestador,
                List.of(StatusServicoEnum.EM_EXECUCAO, StatusServicoEnum.FINALIZADO)
        );

        if(servicos.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum servico prestado");
        }
        
        return servicos.stream()
                .map(ServicoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ServicoDTO> buscarCandidaturasDoPrestador(int idPrestador) {
        List<Servico> servicos = servicoRepository.findByIdPrestadorIdUsuarioAndStatusServico(idPrestador, StatusServicoEnum.ORCAMENTO);

        if(servicos.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma candidatura realizada");
        }
        
        return servicos.stream()
                .map(ServicoDTO::new)
                .collect(Collectors.toList());
    }

    public List<ServicoDTO> buscarPropostasRecebidas(int idPrestador) {

        List<Servico> servicos =  servicoRepository.findByIdPrestadorIdUsuarioAndStatusServicoAndIdEmpresaClienteIsNotNull(
                idPrestador, StatusServicoEnum.ORCAMENTO
        );
        
        if(servicos.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma proposta recebida");
        }

        return servicos.stream()
                .map(ServicoDTO::new)
                .collect(Collectors.toList());

    }
}
