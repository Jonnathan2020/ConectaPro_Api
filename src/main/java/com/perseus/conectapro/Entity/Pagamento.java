package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "TBL_PAGAMENTOS")
public class Pagamento
{
    @Id
    @Column(name = "ID_PAGAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMATO_PAGTO")
    private FormaPagtoEnum formaPagto;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "VALOR_PAGTO")
    private Double valorPagto;

    @Column(name = "DATA_PAGTO")
    private DateFormat dataPagto;

    @Column(name = "DATA_VENC")
    private DateFormat dataVenc;

    @Column(name = "COMPROVANTE")
    private String comprovante;
    


}
