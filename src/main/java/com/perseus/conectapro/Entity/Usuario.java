package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_USUARIO") //classe mae para as classes prestador e empresa/cliemte
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario; //nomeado como o id + nome classe para diferenciar na hora de produzir

    @Column(name = "DOCUMENTO")
    private String documento; //entra como cpf ou cnpj dependendo do regime de trabalho do usuario

    @Column(name = "NOME")
    public String nome;

    @Column(name = "NOME_FANTASIA")
    public String nomeFantasia; //usuario pode escolher qual nome utilizara para o publico

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha; // primeiro assume string e no metodo deve ser criptografada

    @Column(name = "TELEFONE")
    public int telefone; //aceita somente numeros e o sistema deve fazer o ajuste do formato do numero de telefone

    @JoinColumn(name = "ID_CONTRATO")
    @OneToOne
    private Contrato idContrato; //faz referencia ao id da classe contrato, um exemplo do pq o nome da classe é bom ser utilizado no id

    @JoinColumn(name = "ID_ENDERECO")
    @OneToOne
    public Endereco idEndereco;


    public String caminhoFoto; //indica onde está armazenada a imagem dentro das pastas


}
