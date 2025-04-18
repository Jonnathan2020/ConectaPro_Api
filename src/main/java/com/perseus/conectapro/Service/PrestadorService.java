package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.PrestadorCreateDTO;
import com.perseus.conectapro.DTO.PrestadorUpdateDTO;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Entity.Plano;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Repository.AvaliacaoRepository;
import com.perseus.conectapro.Repository.EnderecoRepository;
import com.perseus.conectapro.Repository.PlanoRepository;
import com.perseus.conectapro.Repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private PlanoRepository planoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //cadastrar as informaçoes alem do usuario, faltantes para um prestador
    public Prestador cadastrarPrestador(PrestadorCreateDTO prestadorDTO) {

        Plano plano = planoRepository.findById(prestadorDTO.getIdPlano())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));


        Endereco endereco = new Endereco();
        endereco.setLogradouro(prestadorDTO.getLogradouro());
        endereco.setNumero(prestadorDTO.getNumero());
        endereco.setBairro(prestadorDTO.getBairro());
        endereco.setCidade(prestadorDTO.getCidade());
        endereco.setUf(prestadorDTO.getUf());
        endereco.setCep(prestadorDTO.getCep());
        endereco.setComplemento(prestadorDTO.getComplemento());

        // Persistindo o Endereco
        endereco = enderecoRepository.save(endereco);

        Prestador prestador = new Prestador();
        prestador.setCpf(prestadorDTO.getCpf());
        prestador.setNome(prestadorDTO.getNome());
        prestador.setEmail(prestadorDTO.getEmail());

        //Criptografia da senha
        prestador.setSenha(passwordEncoder.encode(prestadorDTO.getSenha()));

        prestador.setTelefone(prestadorDTO.getTelefone());
        prestador.setTipoUsuario(prestadorDTO.getTipoUsuario());
        prestador.setCaminhoFoto(prestadorDTO.getCaminhoFoto());
        prestador.setIdPlano(plano);

        prestador.setDescPrestador(prestadorDTO.getDescPrestador());
        prestador.setEspecialidades(prestadorDTO.getEspecialidades());
        prestador.setStatusDisponibilidade(prestadorDTO.getStatusDisponibilidade());
        prestador.setEndereco(endereco);


        return prestadorRepository.save(prestador);
    }

    //consultar somente prestadores de servico
    public List<Prestador> consultarPrestadores() {
        return prestadorRepository.findAll(); //precisa ser exibido somente usuarios que são prestadores de serviço
    }

    //consultar prestador especifico
    public Prestador consultarPrestadorUnico(int idUsuario) {
        Prestador prestadorEspecifico = prestadorRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));

        return prestadorEspecifico;
    }

    //consultar pelo nome
    public List<Prestador> consultarPrestadorPorNome(String nome){
        return prestadorRepository.findByNome(nome);
    }

    //consultar pela especialidade
    public List<Prestador> consultarPrestadorPorEspecialidade(String especialidade) {
        return prestadorRepository.findByEspecialidadesContaining(especialidade);
    }

    // Metodo para validação das informações durante a atualização do prestador
    private void validarAtualizacaoPrestador(PrestadorUpdateDTO dto) {
        if (dto.getCpf() != null) {
            String cpfLimpo = dto.getCpf().replaceAll("\\D", "");
            if (cpfLimpo.length() != 11) {
                throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos");
            }
        }

        if (dto.getDescPrestador() != null && dto.getDescPrestador().isBlank()) {
            throw new IllegalArgumentException("A descrição do prestador, se informada, não pode estar em branco");
        }

        if (dto.getEspecialidades() != null && dto.getEspecialidades().isEmpty()) {
            throw new IllegalArgumentException("As especialidades, se informadas, não podem estar vazias");
        }

        if (dto.getStatusDisponibilidade() != null && !dto.getStatusDisponibilidade().name().isBlank()) {
            throw new IllegalArgumentException("Status de disponibilidade inválido");
        }
    }


    //alterar informaçoes somente do prestador
    public Prestador alterarPrestador(int idUsuario, PrestadorUpdateDTO prestadorUpdateDTO) {
        validarAtualizacaoPrestador(prestadorUpdateDTO);

        Prestador prestadorExistente = prestadorRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));


        if (prestadorUpdateDTO.getCpf() != null){
            prestadorExistente.setCpf(prestadorUpdateDTO.getCpf());
        }
        if (prestadorUpdateDTO.getDescPrestador() != null){
            prestadorExistente.setDescPrestador(prestadorUpdateDTO.getDescPrestador());
        }
        if (prestadorUpdateDTO.getEspecialidades() != null){
            prestadorExistente.setEspecialidades(prestadorUpdateDTO.getEspecialidades());
        }
        if (prestadorUpdateDTO.getStatusDisponibilidade() != null){
            prestadorExistente.setStatusDisponibilidade(prestadorUpdateDTO.getStatusDisponibilidade());
        }

        //metodo que salva as informaçoes do prestador
        return prestadorRepository.save(prestadorExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        prestadorRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario
    }

}
