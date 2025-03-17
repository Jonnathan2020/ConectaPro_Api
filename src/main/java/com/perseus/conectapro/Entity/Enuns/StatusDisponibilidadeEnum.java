package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TBL_STATUS_DISPONIBILIDADE")
public enum StatusDisponibilidadeEnum
{
    DISPONIVEL(1, "Disponível"),
    INDISPONIVEL(2, "Indisponível"),
    EM_ATENDIMENTO(3, "Em Atendimento");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STATUS_DISPONIBILIDADE")
    public int id;

    @Column(name = "DESCRICAO_STATUS_DISPONIBILIDADE")
    public String descricao;

    // Construtor da enumeração
    StatusDisponibilidadeEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
