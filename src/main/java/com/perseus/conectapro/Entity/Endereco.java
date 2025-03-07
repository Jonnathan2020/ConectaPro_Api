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
    public int idEndereco;

    @Column(name = "LOGRADOURO")
    public String logradouro;

    @Column(name = "NUMERO")
    public int numero;

    @Column(name = "BAIRRO")
    public String bairro;

    @Column(name = "CIDADE")
    public String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "UF")
    public UfEnum uf;

    @Column(name = "COMPLEMENTO")
    public String complemento;

    /*Acredito que UF seria enum mas por enquanto vou deixar assim */
}

