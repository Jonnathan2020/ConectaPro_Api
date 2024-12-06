package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
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
        public String nome;

        @Column(name = "SOBRENOME")
        public String sobrenome;

        @Column(name = "CNPJ")
        private String cnpj;

        @Column(name = "TELEFONE")
        private int telefone;


        @Column(name = "ID_ENDERECO")
        public Endereco idendereco;

        @Column(name = "DATA_NASCIMENTO")
        private DateFormat dataNascimento;

        @Column(name = "HABILIDADE")
         public String habilidade;

        @Column(name = "ESPECIALIZACAO")
         public String especializacao;

        @Column(name = "ID_STATUS_DISPONIBILIDADE")
        @Enumerated(EnumType.STRING)
        public StatusDisponibilidadeEnum idStatusDisponibilidadeEnum;

        @Column(name = "ID_SOLICITACAO_SERVICO")
        public SolicitacaoServico idSolicitacaoServico;

        @Column(name = "ID_SOLICITACAO_PRESTADOR")
        public PublicacaoServico idSolicitacaoPrestador;

        @Column(name = "ID_PAGAMENTO")
        private Pagamento idPagamento;

        @Column(name = "ID_FATURAMENTO")
        private Faturamento idFaturamento;

        @Column(name = "ID_CONTRATO")
        private Contrato idContrato;

        @Column(name = "FOTO ")
        public BufferedImage foto;

    }
