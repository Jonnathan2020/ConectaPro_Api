package com.perseus.conectapro.Entity.Enuns;

public enum StatusOrcamentoEnum {
    ACEITO(1, "ACEITO"),
    PENDENTE(2, "PENDENTE"),
    RECUSADO(3, "RECUSADO");

    private int id;
    private String descricao;

    StatusOrcamentoEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    // Sobrescrita do metodo toString para retorno da descrição
    @Override
    public String toString() {
        return this.descricao;
    }
}
