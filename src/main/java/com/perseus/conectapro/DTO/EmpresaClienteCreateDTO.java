package com.perseus.conectapro.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaClienteCreateDTO extends UsuarioCreateDTO {

    private String CNPJ;
    private String razaoSocial;
    private String nomeFantasia;

}
