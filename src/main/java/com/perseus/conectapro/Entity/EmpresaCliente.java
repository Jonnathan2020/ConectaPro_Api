package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.DocFlavor;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TBL_EMPRESA_CLIENTE")
public class EmpresaCliente extends Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA_CLIENTE")
    public int idEmpresaCliente;

    @Column(name = "CNPJ")
    public String CNPJ;

    @Column(name = "RAZAO_SOCIAL")
    public String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    public String nomeFantasia; //usuario pode escolher qual nome utilizara para o publico

    @OneToOne
    @JoinColumn(name = "TBL_USUARIO")
    public Usuario idUsuario;


}
