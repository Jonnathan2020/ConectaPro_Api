package com.perseus.conectapro.Entity;

//import com.perseus.conectapro.Entity.Enuns.ufEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ENDERECO")
public class Endereco {
    @Id
    @Column(name = "ID_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private int numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CIDADE")
    private String cidade;
}
//    @Enumerated(EnumType.STRING)
//    @Column(name = "UF")
//    private Enum uf;
//    /*Acredito que UF seria enum mas por enquanto vou deixar assim */

