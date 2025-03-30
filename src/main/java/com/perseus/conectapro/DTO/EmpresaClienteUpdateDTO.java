package com.perseus.conectapro.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaClienteUpdateDTO extends UsuarioUpdateDTO {

    private String CNPJ;
    private String razaoSocial;
    private String nomeFantasia;

}
