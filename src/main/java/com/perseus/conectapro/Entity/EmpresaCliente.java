package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class EmpresaCliente extends Usuario {

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @OneToMany(mappedBy = "idServico")
    private List<Servico> servicos;

    @PrePersist
    @PreUpdate
    public void formatarCnpj() {
        if (cnpj != null) {
            // Remove tudo que não for número
            cnpj = cnpj.replaceAll("\\D", "");

            // Só formata se tiver exatamente 14 dígitos
            if (cnpj.length() == 14) {
                cnpj = cnpj.substring(0, 2) + "." +
                        cnpj.substring(2, 5) + "." +
                        cnpj.substring(5, 8) + "/" +
                        cnpj.substring(8, 12) + "-" +
                        cnpj.substring(12, 14);
            } else {
                throw new IllegalArgumentException("CNPJ deve conter 14 dígitos numéricos");
            }
        }
    }
}
