package com.perseus.conectapro.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private String rua ;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;

    public String getRua() {
        return rua;
    }
}


