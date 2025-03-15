package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.tipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private int idUsuario;

    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "NOME")
    public String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "TELEFONE")
    public Long telefone;

    @JoinColumn(name = "ID_ENDERECO")
    @OneToOne
    public Endereco idEndereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    public tipoUsuarioEnum tipoUsuario;

    public String caminhoFoto;
}
