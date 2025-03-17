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
    public int idOrcamento;

    @OneToOne
    @JoinColumn(name = "ID_SERVICO")
    public Servico idServico;

    @OneToOne
    @JoinColumn(name = "ID_PRESTADOR")
    public Prestador idPrestador;

    @Column(name = "VALOR_ORCAMENTO")
    public float valorOrcamento;

    @Column(name = "PREVISAO_INICIO")
    public LocalDateTime previsaoInicio;

    @Column(name = "DURACAO_SERVICO")
    public int duracaoServico;
}
