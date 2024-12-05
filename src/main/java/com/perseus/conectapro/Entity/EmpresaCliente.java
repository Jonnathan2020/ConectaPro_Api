package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "EMPRESA_CLIENTE")
public class EmpresaCliente
{
    @Id
    @Column(name = "ID_EMPRESA_CLIENTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "RAZA0_SOCIAL")
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    public String nomeFantasia;

    @Column(name = "SEGMENTO")
    public String segmento;

    @Column(name = "TELEFONE")
    private int telefone;

    @Column(name = "ENDERECO_COMERCIAL")
    public Endereco enderecoComercial;

    @Column(name = "PUBLICACAO_PRESTADOR")
    public PublicacaoServico publicacaoPrestador;

    @Column(name = "CONTRATO")
    private Contrato contrato;

    @Column(name = "PAGAMENTO")
    private Pagamento pagamento;

    @Column(name = "FOTO ")
    public BufferedImage foto;


}
