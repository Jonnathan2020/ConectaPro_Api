package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "TBL_HOME_PAGE")
public class HomePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HOME_PAGE")
    private int idHomePage;

    @Column(name = "CAMINHO_FOTO_FUNDO")
    private String caminhoFotoFundo;

    //@OneToMany(mappedBy = "homePage")
   // private List<SolicitacaoServico> solicitacaoServico;

}
