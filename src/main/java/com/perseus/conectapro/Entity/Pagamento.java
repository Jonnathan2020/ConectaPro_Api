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
    public int idPagamento;

    @OneToOne
    @JoinColumn(name = "ID_SERVICO")
    public Servico idServico;

    @Column(name = "VALOR_PAGAMENTO")
    public float valorPagamento;

    @Column(name = "VALOR_PLATAFORMA")
    public float valorPlataforma;

    @Column(name = "VALOR_PRESTADOR")
    public float valorPrestador;

    @Column(name = "SITUACAO_REPASSE")
    public String situacaoRepasse;
}
