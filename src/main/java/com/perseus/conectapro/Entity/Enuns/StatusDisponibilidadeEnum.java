package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_STATUS_DISPONIBILIDADE")
public enum StatusDisponibilidadeEnum
{;
    @Id
    @Column(name = "ID_STATUS_DISPONIBILIDADE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descricao;
}
