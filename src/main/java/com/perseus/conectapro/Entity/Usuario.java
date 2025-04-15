package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.tipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBL_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private int idUsuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "TELEFONE")
    private Long telefone;

    @JoinColumn(name = "ID_ENDERECO")
    @OneToOne
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO")
    private tipoUsuarioEnum tipoUsuario;

    private String caminhoFoto;

    /*IDs compartilhados significam que tanto a Empresa quanto
     o Prestador usam o mesmo ID da classe Usuario, já que
     ambos herdam dessa classe. Quando você registra um
     Pagamento, o campo id_usuario pode ser o ID de uma
     Empresa ou de um Prestador, mas o valor do ID será
     o mesmo para ambos. A tabela Pagamento armazena
     esse ID compartilhado, independentemente do tipo
     de usuário. Isso permite que tanto empresas quanto
     prestadores possam estar associados ao pagamento com
     o mesmo ID. Ou seja, o pagamento pode se referir a
     diferentes tipos de usuários usando o mesmo campo de ID.*/
}
