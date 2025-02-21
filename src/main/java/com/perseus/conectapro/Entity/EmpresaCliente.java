package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

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

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "SEGMENTO")
    private String segmento;

    @Column(name = "TELEFONE")
    private String telefone;

    @OneToOne
    @JoinColumn(name = "ENDERECO_COMERCIAL")
    private Endereco enderecoComercial;

    //Por que teria publicação do prestador na classe da empresa?
    @JoinColumn(name = "PUBLICACAO_PRESTADOR")
    private PublicacaoServico publicacaoPrestador;

    @OneToMany
    @JoinColumn(name = "CONTRATO")
    private List<Contrato> contrato;

    @OneToMany
    @Column(name = "PAGAMENTO")
    private List<Pagamento> pagamento;

    @Column(name = "FOTO")
    private BufferedImage foto;


}
