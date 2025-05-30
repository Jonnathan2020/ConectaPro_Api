package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.SituacaoRepasseEnum;
import com.perseus.conectapro.Entity.Pagamento;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Servico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDTO {
    private int idPagamento;
    private float valorPagamento;
    private float valorPlataforma;
    private float valorPrestador;
    private SituacaoRepasseEnum situacaoRepasseEnum;

    public PagamentoDTO(Pagamento pagamento){
        this.idPagamento = pagamento.getIdPagamento();
        this.valorPagamento = pagamento.getValorPagamento();
        this.valorPlataforma = pagamento.getIdPagamento();
        this.valorPrestador = pagamento.getValorPrestador();
    }
}
