package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Servico;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmpresaClienteDTO {
    private int idEmpresaCliente;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private List<Servico> servicos;
    //private String caminhoFoto;
    private List<SolicitacaoServicoDTO> orcamentos;

    public EmpresaClienteDTO(EmpresaCliente empresaCliente, List<SolicitacaoServicoDTO> orcamentos){
        this.idEmpresaCliente = empresaCliente.getIdUsuario();
        this.razaoSocial = empresaCliente.getRazaoSocial();
        this.nomeFantasia = empresaCliente.getNomeFantasia();
        this.cnpj = empresaCliente.getCnpj();
        this.servicos = empresaCliente.getServicos();
        this.orcamentos = orcamentos;
        //this.caminhoFoto = empresaCliente.getCaminhoFoto();

    }

}
