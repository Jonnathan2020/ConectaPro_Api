package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TBL_PRESTADOR")
@PrimaryKeyJoinColumn(name = "ID_USUARIO")
public class Prestador extends Usuario{

    @ElementCollection
    @CollectionTable(name = "HABILIDADES", joinColumns = @JoinColumn(name = "ID_PRESTADOR"))
    @Column(name = "HABILIDADE")
    private List<String> habilidades;

    @ElementCollection
    @CollectionTable(name = "ESPECIALIDADES", joinColumns = @JoinColumn(name = "ID_PRESTADOR"))
    @Column(name = "ESPECIALIDADE")
    private List<String> especialidades;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_DISPONIBILIDADE")
    private StatusDisponibilidadeEnum statusDisponibilidade;

    @OneToMany(mappedBy = "prestador", cascade = CascadeType.ALL)
    public List<PublicacaoServico> publicacoes;

    @OneToOne
    @JoinColumn(name = "ID_CONTRATO")
    private Contrato idContrato;

    @OneToMany(mappedBy = "TBL_PRESTADOR", cascade = CascadeType.ALL)
    public List<Avaliacao> avaliacoes;

    @Column(name = "IS_PRESTADOR")
    private boolean isPrestador;

    /*ainda nao existe as classes!
        private PrestadorPagamento pagamentos;
        private PrestadorFaturamento faturamentos;
     */
}
