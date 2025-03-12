package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.tipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Cleanup;
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

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha; // primeiro assume string e no metodo deve ser criptografada

    @Column(name = "TELEFONE")
    public int telefone; //aceita somente numeros e o sistema deve fazer o ajuste do formato do numero de telefone

    @JoinColumn(name = "ID_ENDERECO")
    @OneToOne
    public Endereco idEndereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    public tipoUsuarioEnum tipoUsuario;

    public String caminhoFoto; //indica onde está armazenada a imagem dentro das pastas


}
