package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Prestador extends Usuario {
    @Column(name = "DESC_PRESTADOR")
    private String descPrestador;

    @Column(name = "CPF")
    private String cpf;

    @ElementCollection
    @CollectionTable(name = "ESPECIALIDADES", joinColumns = @JoinColumn(name = "ID_USUARIO"))
    @Column(name = "ESPECIALIDADE")
    private List<String> especialidades;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_DISPONIBILIDADE")
    private StatusDisponibilidadeEnum statusDisponibilidade;

    @JoinColumn(name = "ID_PLANO")
    @ManyToOne
    private Plano idPlano;

    @OneToMany(mappedBy = "idPrestador")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "idPrestador")
    private List<Servico> servicos;
}

