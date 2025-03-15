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
public class EmpresaCliente extends Usuario {

    @Column(name = "CNPJ")
    public String CNPJ;

    @Column(name = "RAZAO_SOCIAL")
    public String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    public String nomeFantasia;
}

