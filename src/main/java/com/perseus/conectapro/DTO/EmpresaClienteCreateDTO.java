package com.perseus.conectapro.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaClienteCreateDTO extends UsuarioCreateDTO {

    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos")
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

}
