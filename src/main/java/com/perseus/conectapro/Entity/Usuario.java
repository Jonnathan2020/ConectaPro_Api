package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.tipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Isso indica que a herança será mapeada em uma única tabela
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)  // Define um campo para identificar o tipo
@Getter
@Setter
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    protected String nome;

    @Column(name = "NOME_FANTASIA")
    protected String nomeFantasia;

    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "SENHA")
   protected String senha;

    /*Deveria ser uma lista*/
    @Column(name = "DOCUMENTO")
    protected String documento;

    @Column(name = "TELEFONE")
    protected String telefone;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    /*Pra explicar aqui o código
    * Na tabela usuarios temos o usuario contrato
    * o mesmo foi feito com o atributo endereço
    * e vai ser assim que vou fazer nas outras classes
    * sempre refereniando a tabela a qual estou.*/
    // Relacionamento com os contratos como cliente
    @OneToMany(mappedBy = "usuarioCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratosCliente = new ArrayList<>();

    // Relacionamento com os contratos como prestador
    @OneToMany(mappedBy = "usuarioPrestador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratosPrestador = new ArrayList<>();

    @Column(name = "FOTO_URL")
    public String fotoUrl;

    @Column(name = "TBL_TIPOUSUARIO")
    public tipoUsuarioEnum tipoUsuario;
}
