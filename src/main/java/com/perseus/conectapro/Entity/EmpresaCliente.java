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
@DiscriminatorValue("CLIENTE")  // Especifica que o tipo do usuário é CLIENTE
@Getter
@Setter
@Table(name = "EMPRESA_CLIENTE")
public class EmpresaCliente extends Usuario
{

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLIENTE_SEGMENTO")
    private TipoSegmentoEnum segmentoEnum;

    @Column(name = "TBL_CLIENTE_CONTRATO")
    private Contrato contrato;

    @Column(name = "CLIENTE_PAGAMENTO")
    private ClientePagamento pagamento;

    @Column(name = "CLIENTE_CPNJ")
    private String cnpj;

    @OneToMany
    @JoinColumn(name = "CLIENTE_AVALIACAO")
    private Avaliacao avaliacao;


}
