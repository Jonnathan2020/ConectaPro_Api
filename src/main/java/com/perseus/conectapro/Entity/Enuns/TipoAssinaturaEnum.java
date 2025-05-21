package com.perseus.conectapro.Entity.Enuns;

public enum TipoAssinaturaEnum
{
    START(1, "START"),
    PRO(2, "PRO"),
    EXECUTIVE(3, "EXECUTIVE");

    private int id;
    private String descricao;

    TipoAssinaturaEnum(int id, String descricao){
        this.id = id;
        this.descricao =  descricao;
    }

    @Override
    public String toString(){ return this.descricao;}
}
