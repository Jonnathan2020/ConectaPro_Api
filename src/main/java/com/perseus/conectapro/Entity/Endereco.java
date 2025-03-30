package com.perseus.conectapro.Entity;

//import com.perseus.conectapro.Entity.Enuns.ufEnum;
import com.perseus.conectapro.Entity.Enuns.UfEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_ENDERECO")
public class Endereco {
    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "UF")
    private UfEnum uf;

    @Column(name = "CEP")
    private Integer CEP;

    @Column(name = "COMPLEMENTO")
    private String complemento;
}

