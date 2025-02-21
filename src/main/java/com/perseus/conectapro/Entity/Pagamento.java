package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "TBL_PAGAMENTOS")
public class Pagamento
{
    @Id
    @Column(name = "ID_PAGAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "DADO_CLIENTE")
    private EmpresaCliente dadoCliente;


    @JoinColumn(name = "DADO_Cobrador")
    private Prestador dadoCobrador;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMATO_PAGTO")
    private FormaPagtoEnum formaPagto;

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
