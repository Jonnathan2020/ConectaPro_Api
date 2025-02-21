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
public class Pessoa {

    // NÃO HÁ NECSSIDADE DESTA CLASSE?

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

    @Column(name = "ESPECIALIZACAO")
    public String especializacao;

    @Column(name = "HABILIDADES")
    public List habilidades;

    @Column(name = "ENDERECO")
    public Endereco endereco;

    @Column(name = "STATUS_DISPONIBILIDADE")
    public Enum statusDisponibilidade;

    @OneToMany
    @Column(name = "AVALIACOES")
    public Avaliacao avaliacao;

    @Column(name = "CONTA_BANCARIA")
    private ContaBancaria contaBancaria;

    @Column(name = "PUBLICACAO_SERVICO")
    public PublicacaoServico publicacaoServico;

    @Column(name = "PUBLICACAO_PRESTADOR")
    public PublicacaoServico publicacaoPrestador;

    @Column(name = "CONTRATO")
    private Contrato contrato;

    @Column(name = "PAGAMENTO")
    private Pagamento pagamento;

    @Column(name = "FATURAMENTO")
    private Faturamento faturamento;

    @Column(name = "FOTO ")
    public BufferedImage foto;
    /*para armazenar diretamente uma imagem
    // Getters e Setters
    public BufferedImage getFoto() {
        return foto;
    }
    public void setFoto(BufferedImage foto) {
        this.foto = foto;*/




}
