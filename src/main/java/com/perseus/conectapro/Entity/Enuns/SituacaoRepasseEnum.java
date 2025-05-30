package com.perseus.conectapro.Entity.Enuns;

import lombok.Getter;

@Getter
public enum SituacaoRepasseEnum {
    AGUARDANDO_REPASSE,
    EM_ANALISE,
    REPASSADO,
    PAGAMENTO_FALHOU,
    CANCELADO,
    ESTORNADO

}
