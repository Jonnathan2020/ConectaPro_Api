package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "PAGAMENTO")
public class Pagamento
{
    @Id
    @Column(name = "ID_PAGAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DADO_CLIENTE")
    private Pessoa dadoCliente;

    @Column(name = "DADO_Cobrador")
    private Pessoa dadoCobrador;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "FORMATO_PAGTO")
    private Enum formaPagto;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "VALOR_PAGTO")
    private Double valorPagto;

    @Column(name = "DATA_PAGTO")
    private DateFormat dataPagto;

    @Column(name = "DATA_VENC")
    private DateFormat dataVenc;

    @Column(name = "COMPROVANTE")
    private String comprovante;
    


}
