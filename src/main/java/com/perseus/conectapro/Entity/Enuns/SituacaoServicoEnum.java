package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_SITUACAO_SERVICO")
public enum SituacaoServicoEnum
{
    ORCAMENTO(1, "ORÇAMENTO"),
    APROVADO(2, "APROVADO"),
    AGENDADO(3, "AGENDADO"),
    CANCELADO(4, "CANCELADO"),
    FINALIZADO(5, "FINALIZADO");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SITUACAO_SERVICO")
    public int id;

    @Column(name = "DESC_SITUACAO_SERVICO")
    public String descSituacaoServico;

    SituacaoServicoEnum(int id, String descSituacaoServico) {
        this.id = id;
        this.descSituacaoServico = descSituacaoServico;
    }
}