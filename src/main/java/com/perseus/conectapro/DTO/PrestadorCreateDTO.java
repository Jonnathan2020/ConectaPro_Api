package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PrestadorCreateDTO extends UsuarioCreateDTO{

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    //ID do segmento
    private List<Integer> segmentos;
    private String descPrestador;
    private List<String> especialidades;
    private StatusDisponibilidadeEnum statusDisponibilidade;
    private Long plano;
    private LocalDate dataNascimento;

}
