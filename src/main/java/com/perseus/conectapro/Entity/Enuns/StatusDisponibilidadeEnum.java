package com.perseus.conectapro.Entity.Enuns;

import lombok.Getter;

@Getter
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

    // Sobrescrita do metodo toString para retorno da descrição
    @Override
    public String toString() {
        return this.descricao;
    }
}
