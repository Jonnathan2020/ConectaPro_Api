package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    public String nome;

    @Column(name = "NOME_FANTASIA")
    public String nomeFantasia;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    /*Deveria ser uma lista*/
    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "TBL_USUARIO_ENDERECO")
    private Endereco endereco;

    /*Pra explicar aqui o código
    * Na tabela usuarios temos o usuario contrato
    * o mesmo foi feito com o atributo endereço
    * e vai ser assim que vou fazer nas outras classes
    * sempre refereniando a tabela a qual estou.*/
    @Column(name = "TBL_USUARIO_CONTRATO")
    private Contrato contrato;

    @Column(name = "FOTO_URL")
    public String fotoUrl;
}
