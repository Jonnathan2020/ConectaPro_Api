package com.perseus.conectapro.Entity.Enuns;


public enum StatusServicoEnum {
    ORCAMENTO(1, "ORÇAMENTO"),
    RECUSADO(2, "RECUSADO"),
    PENDENTE_PAGTO(3, "PENDENTE PAGAMENTO"),
    PENDENTE_INICIO(4, "PENDENTE INICIO"),
    EM_EXECUCAO(5, "EM EXECUÇÃO"),
    PENDENTE_CONFIRMAR_FINALIZACAO(6, "PENDENTE CONFIRMAR FINALIZACAO"),
    FINALIZADO(7, "FINALIZADO"),
    AGENDADO(8, "AGENDADO"),
    CANCELADO(9, "CANCELADO");
    private final int id;
    private final String descricao;

    // Construtor da enumeração
    StatusServicoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}