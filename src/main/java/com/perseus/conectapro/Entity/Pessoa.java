package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
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

    @Column(name = "RG")
    private String rg;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "NOME")
    public String nome;

    @Column(name = "SOBRENOME")
    public String sobrenome;

    @Column(name = "DATA_NASCIMENTO")
    private DateFormat dataNascimento;

    @Column(name = "TELEFONE")
    private int telefone;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "SEGMENTO")
    private String segmento;

    @Column(name = "FOTO ")
    public BufferedImage foto;
                                    /*para armazenar diretamente uma imagem
                                // Getters e Setters
                                public BufferedImage getFoto() {
                                    return foto;
                                }
                                public void setFoto(BufferedImage foto) {
                                    this.foto = foto;
                                */
    @Column(name = "ESPECIALIZACAO")
    public String especializacao;

    @Column(name = "HABILIDADES")
    public List habilidades;

    @Column(name = "ENDERECO_COMERCIAL")
    public EnderecoComercial enderecoComercial;

    @Column(name = "ENDERECO_RESIDENCIAL")
    public EnderecoResidencial enderecoResidencial;

    @Column(name = "STATUS_DISPONIBILIDADE")
    public Enum statusDisponibilidade;

    @Column(name = "AVALIACOES")
    public Avaliacoes avaliacoes;

    @Column(name = "CONTA_BANCARIA")
    private ContaBancaria contaBancaria;

    @Column(name = "PUBLICACAO_SERVICO")
    public PublicacaoServico publicacaoServico;


    @Column(name = "ORDEM_SERVICO")
    private OrdemServico ordemServico;

    @Column(name = "CONTRATO")
    private Contrato contrato;

    @Column(name = "PAGAMENTO")
    private Pagamento pagamento;




}
