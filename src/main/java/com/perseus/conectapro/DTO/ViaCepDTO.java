package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.UfEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade; // cidade
    private UfEnum uf;
    private Boolean erro;

}
