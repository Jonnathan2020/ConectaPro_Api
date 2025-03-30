package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name= "TBL_ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORCAMENTO")
    private int idOrcamento;

    @OneToOne
    @JoinColumn(name = "ID_SERVICO")
    private Servico idServico;

    @OneToOne
    @JoinColumn(name = "ID_PRESTADOR")
    private Prestador idPrestador;

    @Column(name = "VALOR_ORCAMENTO")
    private float valorOrcamento;

    @Column(name = "PREVISAO_INICIO")
    private LocalDateTime previsaoInicio;

    @Column(name = "DURACAO_SERVICO")
    private int duracaoServico;
}
