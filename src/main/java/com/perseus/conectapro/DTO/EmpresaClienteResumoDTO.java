package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmpresaClienteResumoDTO {
    private int idEmpresaCliente;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String caminhoFoto;

    public EmpresaClienteResumoDTO(EmpresaCliente cliente) {
        this.idEmpresaCliente = cliente.getIdUsuario();
        this.cnpj = cliente.getCnpj();
        this.razaoSocial = cliente.getRazaoSocial();
        this.nomeFantasia = cliente.getNomeFantasia();
        this.caminhoFoto = cliente.getCaminhoFoto();
    }
}