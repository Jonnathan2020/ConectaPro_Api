package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.StatusRepasseEnum;
import com.perseus.conectapro.Entity.Pagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDTO {
    private int idPagamento;
    private float valorPagamento;
    private float valorPlataforma;
    private float valorPrestador;
    private StatusRepasseEnum statusRepasse;
    private PrestadorResumoDTO recebedor;

    public PagamentoDTO(Pagamento pagamento){
        this.idPagamento = pagamento.getIdPagamento();
        this.valorPagamento = pagamento.getValorPagamento();
        this.valorPlataforma = pagamento.getIdPagamento();
        this.valorPrestador = pagamento.getValorPrestador();
        this.recebedor = new PrestadorResumoDTO(pagamento.getRecebedor());
    }
}
