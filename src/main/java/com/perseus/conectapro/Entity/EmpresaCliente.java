package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "TBL_EMPRESA_CLIENTE")
@PrimaryKeyJoinColumn(name = "ID_USUARIO")
public class EmpresaCliente extends Usuario
{
    @Column(name = "RAZAO_SOCIAL")
    public String razaoSocial;

    @Column(name = "SEGMENTO")
    public String segmento;

    @OneToOne
    @JoinColumn(name = "ID_CONTRATO")
    private Contrato idContrato;

    @OneToMany(mappedBy = "TBL_EMPRESA_CLIENTE", cascade = CascadeType.ALL)
    public List<Avaliacao> avaliacoes;

    @Column(name = "IS_EMPRESA_CLIENTE")
    private boolean isEmpresaCliente;


}
