package com.perseus.conectapro.Entity.Enuns;

public enum TipoCategoriaEnum {
    TECNOLOGIA(1, "TECNOLOGIA"),
    MARKETING(2, "MARKETING"),
    DESIGN(3,"DESIGN"),
    REDES(4,"REDES"),
    INTERNET(5,"INTERNET"),
    TELECOMUNICAÇÕES(6,"TELECOMUNICAÇÕES"),
    SEGURANÇA(7, "SEGURANÇA"),
    MONITORAMENTO(8, "MONITORAMENTO"),
    RESIDENCIAL(9, "RESIDENCIAL"),
    REFRIGERAÇÃO(10, "REFRIGERAÇÃO"),
    ELETRICA(11, "ELÉTRICA"),
    CONSTRUÇÃO(12, "CONSTRUÇÃO"),
    REPAROS(13, "REPAROS"),
    JARDINAGEM(14, "JARDINAGEM"),
    CONTABIL(15, "CONTABIL"),
    BELEZA(16, "BELEZA"),
    ESTETICA(17, "ESTETICA"),
    OUTROS(18, "OUTROS");

    private int id;
    private String descricao;

    TipoCategoriaEnum(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public String toString(){return this.descricao;}

}
