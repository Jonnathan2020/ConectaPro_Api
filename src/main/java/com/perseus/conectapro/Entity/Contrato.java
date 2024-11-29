package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

@Entity
@Getter
@Setter
@Table(name = "CONTRATO")
public class Contrato
{
    @Id
    @Column(name = "ID_CONTRATO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "ORDEM_SERVICO")
    private OrdemServico ordemServico;



    @Column(name = "OBJETO_CONTRATO")
    public String objetoContrato;

    @Column(name = "CLASULAS")
    public String clasulas;

    @Column(name = "DESCRICAO")
    public String descricao;





}
