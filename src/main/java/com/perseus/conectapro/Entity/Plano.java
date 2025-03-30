package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@Getter
@Setter
@Table(name = "TBL_PLANO")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLANO")
    private int idPlano;  //identificador

    @Column(name = "DESC_PLANO")
    private String descPlano; // descricao ou nome do plano

    @Column(name = "VALOR_FIXO_PLANO")
    private Float valorFixoPlano; //valor fixo do plano

    @Column(name = "PERCENTUAL_PLANO")
    private Float percentualPlano; //percentual sobre valor de comissionamento do plano
}