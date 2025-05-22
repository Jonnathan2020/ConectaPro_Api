package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusOrcamentoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "TBL_ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORCAMENTO")
    private int idOrcamento;

    @Column(name = "TITULO_ORCAMENTO")
    private String tituloOrcamento;

    @Column(name = "DESC_ORCAMENTO")
    private String descOrcamento;

    @OneToMany(mappedBy = "orcamento")
    private List<Servico> servicos;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESACLIENTE")
    private EmpresaCliente idEmpresaCliente;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTADOR")
    private Prestador idPrestador;

    @Column(name = "DATA_INCLUSAO")
    private LocalDateTime dataInclusao;

    @Column(name = "VALOR_ORCAMENTO")
    private BigDecimal valorOrcamento;

    @Column(name = "PREVISAO_INICIO")
    private LocalDate previsaoInicio;

    @Column(name = "DURACAO_SERVICO")
    private int duracaoServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "NIVEL_URGENCIA")
    private NvlUrgenciaEnum nvlUrgenciaEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CATEGORIA")
    private TipoCategoriaEnum tipoCategoriaEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_PAGTO")
    private FormaPagtoEnum formaPagtoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_ORCAMENTO")
    private StatusOrcamentoEnum statusOrcamentoEnum;

}
