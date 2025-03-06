package com.perseus.conectapro.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private String nome;
    private String email;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String segmento; // TipoSegmentoEnum pode ser mapeado em backend
    private String telefone;
    private String cep;
    private String nro; // Número do endereço
    private String senha;
}
