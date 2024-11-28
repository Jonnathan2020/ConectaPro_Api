package com.perseus.conectapro.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "OrdemServico")
public class OrdemServico {
    public class Clientes {
        @Id
        @Column(name = "ID_ORDEM")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "CNPJ")
        private String cnpj;

        @Column(name = "RAZAO_SOCIAL")
        private String razaoSocial;

        @Column(name = "NOME_FANTASIA")
        private String nomeFantasia;

        @Column(name = "SEGMENTO")
        private String segmento;

        @Column(name = "TELEFONE")
        private double telefone;

        @Column(name = "ENDERECO")
        private String endereco;

        @Column(name = "BAIRRO")
        private String bairro;

        @Column(name = "CIDADE")
        private String cidade;

    }
}
