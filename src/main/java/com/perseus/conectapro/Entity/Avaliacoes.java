package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AVALIAÇÕES")
public class Avaliacoes {
    @Id
    @Column(name = "ID_AVALIACAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ID_ORDEM_SERVICO")
    private OrdemServico idOrdemServico;
    @Column(name = "ID_PESSOA")
    private Pessoa idPessoa;
    @Column(name = "DESCRICAO")
    private String Descricao;

    //private NvlSatisfacaoEnum nvlSatisfacao; - NvlSatisfacaoEnum ainda não existe
}
