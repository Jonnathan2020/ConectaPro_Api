package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.NvlSatisfacaoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AVALIACOES")
public class Avaliacao {
    @Id
    @Column(name = "ID_AVALIACAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "ID_SERVICO")
    public Servico idServico;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente idEmpresaCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "NVL_SATISFACAO")
    private NvlSatisfacaoEnum nvlSatisfacao;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTADOR")
    private Prestador idPrestador;
}