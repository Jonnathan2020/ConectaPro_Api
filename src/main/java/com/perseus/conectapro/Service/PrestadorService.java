package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.PrestadorCreateDTO;
import com.perseus.conectapro.DTO.PrestadorUpdateDTO;
import com.perseus.conectapro.DTO.ViaCepDTO;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import com.perseus.conectapro.Entity.Plano;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Segmento;
import com.perseus.conectapro.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    private SegmentoRepository segmentoRepository;


    //cadastrar as informaçoes alem do usuario, faltantes para um prestador
    public Prestador cadastrarPrestador(PrestadorCreateDTO prestadorDTO) {

        if (prestadorRepository.existsByEmail(prestadorDTO.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Plano plano = planoRepository.findById(prestadorDTO.getPlano())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        List<Segmento> segmentos = new ArrayList<>();
        if (prestadorDTO.getSegmentos() != null && !prestadorDTO.getSegmentos().contains(null)) {
            segmentos = segmentoRepository.findAllById(prestadorDTO.getSegmentos());

            if (segmentos.size() != prestadorDTO.getSegmentos().size()) {
                throw new RuntimeException("Um ou mais Segmentos não foram encontrados");
            }
        }

        ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(prestadorDTO.getCep());

        Endereco endereco = new Endereco();
        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());
        endereco.setNumero(prestadorDTO.getNumero());
        endereco.setComplemento(prestadorDTO.getComplemento());
        endereco = enderecoRepository.save(endereco);

        Prestador prestador = new Prestador();
        prestador.setCpf(prestadorDTO.getCpf());
        prestador.setNome(prestadorDTO.getNome());
        prestador.setEmail(prestadorDTO.getEmail());
        prestador.setSenha(passwordEncoder.encode(prestadorDTO.getSenha()));
        prestador.setTelefone(prestadorDTO.getTelefone());
        prestador.setDataNascimento(prestadorDTO.getDataNascimento());
        prestador.setTipoUsuario(TipoUsuarioEnum.PRESTADOR);
        prestador.setCaminhoFoto(prestadorDTO.getCaminhoFoto());
        prestador.setPlano(plano);
        prestador.setRole(prestadorDTO.getRole());
        prestador.setDescPrestador(prestadorDTO.getDescPrestador());
        prestador.setEspecialidades(prestadorDTO.getEspecialidades());
        prestador.setStatusDisponibilidade(prestadorDTO.getStatusDisponibilidade());
        prestador.setSegmentos(segmentos); // lista pode estar vazia, sem problemas
        prestador.setEndereco(endereco);

        return prestadorRepository.save(prestador);
    }


    //consultar somente prestadores de servico
    public List<Prestador> consultarPrestadores() {
        return prestadorRepository.findAll(); //precisa ser exibido somente usuarios que são prestadores de serviço
    }

    //consultar prestador especifico
    public List<Prestador> consultarPrestadorUnico(int id) {
        List<Prestador> prestadorEspecifico = prestadorRepository.findByIdUsuario(id);
        //.orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));
        return prestadorEspecifico;
    }

    //consultar pelo nome
    public List<Prestador> consultarPrestadorPorNome(String nome){
        return prestadorRepository.findByNomeContainingIgnoreCase(nome);
    }

    //consultar pela especialidade
    public List<Prestador> consultarPrestadorPorEspecialidades(String especialidades) {
        return prestadorRepository.findByEspecialidadesContaining(especialidades);
    }

    //consultar pelo segmento
    public List<Prestador> consultarPrestadoresPorSegmento(int idSegmento) {
        Segmento segmento = segmentoRepository.findById(idSegmento)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segmento não encontrado"));

        List<Prestador> prestadores = prestadorRepository.findBySegmentosContaining(segmento);

        if (prestadores.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum prestador encontrado para o segmento informado.");
        }

        return prestadores;
    }


    public List<Prestador> consultarPrestadorPorStatusDisponiblidade(StatusDisponibilidadeEnum status) {
        return prestadorRepository.findByStatusDisponibilidade(status);
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

        if (dto.getDataNascimento() != null) {
            LocalDate hoje = LocalDate.now();
            LocalDate dataNascimento = dto.getDataNascimento();

            if (dataNascimento.isAfter(hoje)) {
                throw new IllegalArgumentException("A data de nascimento não pode ser no futuro");
            }

            // Exemplo: idade mínima de 18 anos
            int idade = Period.between(dataNascimento, hoje).getYears();
            if (idade < 18) {
                throw new IllegalArgumentException("O prestador deve ter pelo menos 18 anos");
            }
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

        if (prestadorUpdateDTO.getDataNascimento() != null) {
            prestadorExistente.setDataNascimento(prestadorUpdateDTO.getDataNascimento());
        }

        //metodo que salva as informaçoes do prestador
        return prestadorRepository.save(prestadorExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        prestadorRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario
    }

}
