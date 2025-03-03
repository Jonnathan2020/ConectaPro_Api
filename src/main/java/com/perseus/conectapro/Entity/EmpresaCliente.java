package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoSegmentoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "EMPRESA_CLIENTE")
public class EmpresaCliente extends Usuario
{

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEGMENTO")
    private TipoSegmentoEnum segmentoEnum;

    @Column(name = "TBL_CLIENTE_CONTRATO")
    private Contrato contrato;

    @Column(name = "TBL_CLIENTE_PAGAMENTO")
    private ClientePagamento pagamento;


    @OneToMany
    @JoinColumn(name = "TBL_CLIENTE_AVALIACAO")
    private Avaliacao avaliacao;


}
