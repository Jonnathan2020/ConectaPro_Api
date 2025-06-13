package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.PagamentoDTO;
import com.perseus.conectapro.Entity.Pagamento;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoDTO consultarPagamentosUsuario(){

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Pagamento> pagamentos  =  pagamentoRepository.findByRecebedor_IdUsuario(usuario.getIdUsuario());
        if(pagamentos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return (PagamentoDTO) pagamentos.stream()
                .map(PagamentoDTO::new)
                .collect(Collectors.toList());
    }
}
