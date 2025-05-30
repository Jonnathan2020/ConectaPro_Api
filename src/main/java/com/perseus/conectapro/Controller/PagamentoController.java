package com.perseus.conectapro.Controller;

import com.azure.core.annotation.Get;
import com.perseus.conectapro.DTO.PagamentoDTO;
import com.perseus.conectapro.Service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;
    @GetMapping
    public PagamentoDTO consultarPagamentosUsuario(){
        return pagamentoService.consultarPagamentosUsuario();
    }
}
