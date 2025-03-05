package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

@Entity
@DiscriminatorValue("PRESTADOR")
@Getter
@Setter
@Table(name = "TBL_PRESTADORES")
public class Prestador extends Usuario
{

        @Column(name = "HABILIDADE")
        private List<Prestador> habilidade;

        @Column(name = "ESPECIALIZACAO")
        private List<Prestador> especializacao;

        @Column(name = "STATUS_DISPONIBILIDADE")
        @Enumerated(EnumType.STRING)
        private StatusDisponibilidadeEnum statusDisponibilidade;


        @Column(name = "TBL_PRESTADOR_PRESTADOR_PAGAMENTO")
        private PrestadorPagamento pagamento;

        @Column(name = "TBL_PRESTADOR_PRESTADOR_FATURAMENTO")
        private PrestadorFaturamento faturamento;

        /*
        @JoinColumn(name = "ID_FATURAMENTO")
        private Faturamento idFaturamento;
         */

        @OneToMany
        @JoinColumn(name = "TBL_PRESTADOR_CONTRATO")
        private Contrato contrato;

        @OneToMany
        @JoinColumn(name = "TBL_PRESTADOR_AVALIACAO")
        private Avaliacao avaliacao;
    }
