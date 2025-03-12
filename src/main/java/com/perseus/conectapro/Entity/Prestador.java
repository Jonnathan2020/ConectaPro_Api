package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.Plano;
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
public class Prestador extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESTADOR")
    public int idPrestador;

    @Column(name = "DESC_PRESTADOR")
    public String descPrestador;

    @ElementCollection
    @CollectionTable(name = "ESPECIALIDADES", joinColumns = @JoinColumn(name = "ID_PRESTADOR"))
    @Column(name = "ESPECIALIDADE")
    public List<String> especialidades;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_DISPONIBILIDADE")
    public StatusDisponibilidadeEnum statusDisponibilidade;

    @JoinColumn(name = "TBL_USUARIO")
    @OneToOne
    public Usuario idUsuario;

    @JoinColumn(name = "TBL_PLANO")
    @OneToOne
    public Plano idPlano;
}
