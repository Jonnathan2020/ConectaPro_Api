package com.perseus.conectapro.Entity.Enuns;

public enum NvlSatisfacaoEnum {
    MUITO_SATISFEITO(1,"MUITO SATISFEITO"),
    SATISFEITO(2,"SATISFEITO"),
    POUCO_SATISFEITO(3,"POUCO SATISFEITO"),
    INSATISFEITO(4,"INSATISFEITO");

    private int id;
    private String descricao;

    NvlSatisfacaoEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public String toString(){return this.descricao;}
}
