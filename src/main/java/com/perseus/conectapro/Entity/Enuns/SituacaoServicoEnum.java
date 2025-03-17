package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_SITUACAO_SERVICO")
public enum SituacaoServicoEnum
{
    ORCAMENTO("O", "ORÇAMENTO"),
    APROVADO("A", "APROVADO"),
    AGENDADO("G", "AGENDADO"),
    CANCELADO("C", "CANCELADO"),
    FINALIZADO("F", "FINALIZADO");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SITUACAO_SERVICO")
    public String id;

    @Column(name = "DESC_SITUACAO_SERVICO")
    public String descSituacaoServico;

    SituacaoServicoEnum(String id, String descSituacaoServico) {
        this.id = id;
        this.descSituacaoServico = descSituacaoServico;
    }
}