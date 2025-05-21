package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.*;

public enum SituacaoServicoEnum {
    ORCAMENTO(1, "ORÇAMENTO"),
    APROVADO(2, "APROVADO"),
    PENDENTE_PAGTO(3, "PENDENTE PAGAMENTO"),
    AGENDADO(3, "AGENDADO"),
    CANCELADO(4, "CANCELADO"),
    FINALIZADO(5, "FINALIZADO");

    private final int id;
    private final String descricao;

    // Construtor da enumeração
    SituacaoServicoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}