package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "PESSOA")
public class Pessoa
{
    @Id
    @Column(name = "ID_PESSOA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private String rg;

    @Column(name = "NOME")
    public String nome;

    @Column(name = "SOBRENOME")
    public String sobrenome;

    @Column(name = "DATA_NASCIMENTO")
    private DateFormat dataNascimento;

    @Column(name = "TELEFONE")
    private int telefone;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUM_ENDERECO")
    private int numEndereco;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "FOTO ")
    public BufferedImage foto; /*para armazenar diretamente uma imagem
                                // Getters e Setters
                                public BufferedImage getFoto() {
                                    return foto;
                                }
                                public void setFoto(BufferedImage foto) {
                                    this.foto = foto;
                                */

}
