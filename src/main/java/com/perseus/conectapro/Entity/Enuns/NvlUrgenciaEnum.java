package com.perseus.conectapro.Entity.Enuns;

import lombok.Getter;

@Getter
public enum NvlUrgenciaEnum {
    EMERGENTE(1, "EMERGENTE"),
    URGENTE(2, "URGENTE"),
    POUCO_URGENTE(3, "POUCO URGENTE"),
    NAO_URGENTE(4, "NÃO URGENTE");

    private int id;
    private String descricao;

    NvlUrgenciaEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    // Sobrescrita do metodo toString para retorno da descrição
    @Override
    public String toString() {
        return this.descricao;
    }
}
