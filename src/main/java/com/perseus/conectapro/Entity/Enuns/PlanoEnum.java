package com.perseus.conectapro.Entity.Enuns;


import lombok.Getter;

@Getter
public enum PlanoEnum {
    STANDARD(1, "STANDARD", 19.90f, 10.0f),
    PREMIUM(2, "PREMIUM", 39.90f, 5.0f),
    PRO(3, "PRO", 59.90f, 2.5f);

    private final int idPlano;
    private final String descPlano;
    private final float valorFixoPlano;
    private final float percentualPlano;

    PlanoEnum(int idPlano, String descPlano, float valorFixoPlano, float percentualPlano) {
        this.idPlano = idPlano;
        this.descPlano = descPlano;
        this.valorFixoPlano = valorFixoPlano;
        this.percentualPlano = percentualPlano;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public String getDescPlano() {
        return descPlano;
    }

    public float getValorFixoPlano() {
        return valorFixoPlano;
    }

    public float getPercentualPlano() {
        return percentualPlano;
    }
}
