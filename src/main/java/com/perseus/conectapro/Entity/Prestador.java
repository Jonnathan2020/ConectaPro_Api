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
@Table(name = "PRESTADOR")
public class Prestador
{

        @Id
        @Column(name = "ID_PRESTADOR")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "ENDERECO")
        public Endereco endereco;

        @Column(name = "CPF")
        private String cpf;

        @Column(name = "CNPJ")
        private String cnpj;

        @Column(name = "TELEFONE")
        private int telefone;

        @Column(name = "NOME")
        public String nome;

        @Column(name = "SOBRENOME")
        public String sobrenome;

        @Column(name = "DATA_NASCIMENTO")
        private DateFormat dataNascimento;

        @Column(name = "HABILIDADE")
         public String habilidade;

        @Column(name = "ESPECIALIZACAO")
         public String especializacao;

        @Column(name = "STATUS_DISPONIBILIDADE_ENUM")
        @Enumerated(EnumType.STRING)
        public StatusDisponibilidadeEnum statusDisponibilidadeEnum;

        @Column(name = "PUBLICACAO_SERVICO")
        public PublicacaoServico publicacaoServico;

        @Column(name = "PUBLICACAO_PRESTADOR")
        public PublicacaoServico publicacaoPrestador;

        @Column(name = "PAGAMENTO")
        private Pagamento pagamento;

        @Column(name = "FATURAMENTO")
        private Faturamento faturamento;

        @Column(name = "CONTRATO")
        private Contrato contrato;

        @Column(name = "FOTO ")
        public BufferedImage foto;

    }
