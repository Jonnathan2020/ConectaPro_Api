package com.perseus.conectapro.Entity;

import com.perseus.conectapro.DTO.PrestadorResumoDTO;
import com.perseus.conectapro.Entity.Enuns.StatusRepasseEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_PAGAMENTOS")
public class Pagamento
{
    @Id
    @Column(name = "ID_PAGAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPagamento;

    @JoinColumn(name = "ID_PRESTADOR")
    @ManyToOne
    private Prestador recebedor;

    @OneToOne
    @JoinColumn(name = "ID_SERVICO")
    private Servico idServico;

    @Column(name = "VALOR_PAGAMENTO")
    private float valorPagamento;

    @Column(name = "VALOR_PLATAFORMA")
    private float valorPlataforma;

    @Column(name = "VALOR_PRESTADOR")
    private float valorPrestador;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_REPASSE")
    private StatusRepasseEnum statusRepasseEnum;
}
