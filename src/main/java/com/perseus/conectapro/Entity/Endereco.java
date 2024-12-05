package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ENDERECO")
public class Endereco
{
    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "RUA")
    String rua;

    @Column(name = "NUMERO")
    int numero;

    @Column(name = "COMPLEMENTO")
    String complemento;

    @Column(name = "CIDADE")
    String cidade;

    @Column(name = "UF")
    String uf;
    /*Acredito que UF seria enum mas por enquanto vou deixar assim */
}
