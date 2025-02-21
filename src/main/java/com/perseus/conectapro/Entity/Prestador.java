package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TBL_PRESTADORES")
public class Prestador
{

        @Id
        @Column(name = "ID_PRESTADOR")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "CPF")
        private String cpf;

        @Column(name = "NOME")
        private String nome;

        @Column(name = "SOBRENOME")
        private String sobrenome;

        @Column(name = "CNPJ")
        private String cnpj;

        @Column(name = "TELEFONE")
        private String telefone;

        @OneToOne
        @JoinColumn(name = "ID_ENDERECO")
        private Endereco idEndereco;

        @Column(name = "DATA_NASCIMENTO")
        private DateFormat dataNascimento;

        @Column(name = "HABILIDADE")
         private String habilidade;

        @Column(name = "ESPECIALIZACAO")
         private String especializacao;

        @Column(name = "STATUS_DISPONIBILIDADE")
        @Enumerated(EnumType.STRING)
        private StatusDisponibilidadeEnum statusDisponibilidade;

        @OneToMany
        @JoinColumn(name = "ID_SOLICITACAO_SERVICO")
        private List<SolicitacaoServico> idSolicitacaoServico;

        @OneToMany
        @JoinColumn(name = "ID_SOLICITACAO_PRESTADOR")
        private List<PublicacaoServico> idSolicitacaoPrestador;

        @OneToMany
        @JoinColumn(name = "ID_PAGAMENTO")
        private List<Pagamento> idPagamento;

        /*
        @JoinColumn(name = "ID_FATURAMENTO")
        private Faturamento idFaturamento;
         */

        @OneToMany
        @JoinColumn(name = "ID_CONTRATO")
        private List<Contrato> idContrato;

        @Column(name = "FOTO")
        private BufferedImage foto;

        @OneToMany
        @JoinColumn(name = "ID_AVALIACAO")
        private List<Avaliacao> idAvaliacao;

    }
