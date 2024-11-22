package com.perseus.conectapro.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "CATEGORIAS")
public class Categorias {
    @Id
    @Column(name = "ID_CATEGORIA")
    private Long id;


}
