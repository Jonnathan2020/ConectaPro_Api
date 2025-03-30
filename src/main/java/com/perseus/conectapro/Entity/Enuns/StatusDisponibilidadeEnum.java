package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public enum StatusDisponibilidadeEnum {
    DISPONIVEL(1, "Disponível"),
    INDISPONIVEL(2, "Indisponível"),
    EM_ATENDIMENTO(3, "Em Atendimento");

    private final int id;
    private final String descricao;

    // Construtor da enumeração
    StatusDisponibilidadeEnum(int id, String descricao) {
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
