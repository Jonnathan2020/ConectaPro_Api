package com.perseus.conectapro.Entity.Enuns;

public enum StatusSolicitacaoEnum {
    ACEITA(1, "ACEITA"),
    PENDENTE(2, "PENDENTE"),
    RECUSADA(3, "RECUSADA"),
    DESATIVADA(4,"DESATIVADA");

    private int id;
    private String descricao;

    StatusSolicitacaoEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    // Sobrescrita do metodo toString para retorno da descrição
    @Override
    public String toString() {
        return this.descricao;
    }
}
