package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.UfEnum;
import com.perseus.conectapro.Entity.Enuns.tipoUsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {

    @NotBlank(message = "O nome de usuário não pode estar vazio")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O email do usuário não pode estar vazio")
    private String email;

    @NotBlank(message = "A senha do usuário não pode estar vazia")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotNull(message = "O telefone não pode estar vazio")
    @Pattern(
            regexp = "\\d{10,11}",
            message = "O telefone deve conter entre 10 e 11 dígitos numéricos"
    )
    private String telefone;

    @NotNull(message = "O tipo de usuário não pode estar vazio")
    private tipoUsuarioEnum tipoUsuario;

    private String caminhoFoto;

    @NotBlank(message = "O logradouro não pode estar vazio")
    private String logradouro;

    @NotNull(message = "O número não pode estar vazio")
    private int numero;

    @NotBlank(message = "O bairro não pode estar vazio")
    private String bairro;

    @NotBlank(message = "A cidade não pode estar vazia")
    private String cidade;

    @NotNull(message = "A UF não pode estar vazia")
    private UfEnum uf;

    @NotBlank(message = "O CEP não pode estar vazio")
    private String cep;

    private String complemento;
}
