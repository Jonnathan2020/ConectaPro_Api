package com.perseus.conectapro.Entity.Enuns;

public enum StatusSolicitacaoEnum {
    ACEITA(1, "ACEITA"),
    PENDENTE(2, "PENDENTE"),
    RECUSADA(3, "RECUSADA"),
    ATIVA(4, "ATIVA"),
    INATIVA(5,"INATIVA");

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
