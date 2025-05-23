package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.*;
import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import com.perseus.conectapro.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

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
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //cadastrar as informaçoes alem do usuario, faltantes para um prestador
    public Prestador cadastrarPrestador(PrestadorCreateDTO prestadorDTO) {

        Plano plano = planoRepository.findById(prestadorDTO.getPlano())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(prestadorDTO.getCep());

        Endereco endereco = new Endereco();
        //Serao definidos após a inserção do cep
        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());

        //Usuario definirá manualmente
        endereco.setNumero(prestadorDTO.getNumero());
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
        prestador.setTipoUsuario(TipoUsuarioEnum.PRESTADOR);
        prestador.setCaminhoFoto(prestadorDTO.getCaminhoFoto());
        prestador.setIdPlano(plano);
        prestador.setRole(prestadorDTO.getRole());
        prestador.setDescPrestador(prestadorDTO.getDescPrestador());
        prestador.setEspecialidades(prestadorDTO.getEspecialidades());
        prestador.setStatusDisponibilidade(prestadorDTO.getStatusDisponibilidade());
        prestador.setTipoCategoria(prestadorDTO.getTipoCategoria());
        prestador.setEndereco(endereco);


        return prestadorRepository.save(prestador);
    }

    //consultar somente prestadores de servico
    public List<PrestadorDTO> consultarPrestadores(Specification<Prestador> spec) {
        List<Prestador> prestadores = prestadorRepository.findAll(spec);

        if (prestadores.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum prestador encontrado com os filtros fornecidos.");
        }

        return prestadores.stream().map(prestador -> {
            List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(prestador);

            List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                    .map(orcamento -> new OrcamentoDTO(
                            orcamento.getIdOrcamento(),
                            orcamento.getTituloOrcamento(),
                            orcamento.getDescOrcamento(),
                            orcamento.getValorOrcamento(),
                            orcamento.getDataInclusao(),
                            orcamento.getPrevisaoInicio(),
                            orcamento.getDuracaoServico(),
                            orcamento.getFormaPagtoEnum(),
                            orcamento.getNvlUrgenciaEnum(),
                            orcamento.getTipoCategoriaEnum(),
                            orcamento.getStatusOrcamentoEnum()
                    )).collect(Collectors.toList());


            return new PrestadorDTO(prestador, orcamentoDTOS);
        }).collect(Collectors.toList());
    }

    //consultar prestador especifico
    public PrestadorDTO consultarPrestadorUnico(int id) {
        Prestador prestadorEspecifico = prestadorRepository.findByIdUsuario(id);
        if (prestadorEspecifico == null) {
            throw new IllegalArgumentException("Prestador não encontrado!");
        }

        List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(prestadorEspecifico);

        List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                .map(orcamento -> new OrcamentoDTO(
                        orcamento.getIdOrcamento(),
                        orcamento.getTituloOrcamento(),
                        orcamento.getDescOrcamento(),
                        orcamento.getValorOrcamento(),
                        orcamento.getDataInclusao(),
                        orcamento.getPrevisaoInicio(),
                        orcamento.getDuracaoServico(),
                        orcamento.getFormaPagtoEnum(),
                        orcamento.getNvlUrgenciaEnum(),
                        orcamento.getTipoCategoriaEnum(),
                        orcamento.getStatusOrcamentoEnum()
                )).collect(Collectors.toList());


        return new PrestadorDTO(prestadorEspecifico, orcamentoDTOS);
    }

    //consultar pelo nome
    public List<PrestadorDTO> consultarPrestadorPorNome(String nome) {
        List<Prestador> prestadores = prestadorRepository.findByNomeContainingIgnoreCase(nome);

        if (prestadores == null) {
            throw new IllegalArgumentException("Nenhum Prestador encontrado!");
        }

        return prestadores.stream().map(prestador -> {
            List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(prestador);

            List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                    .map(orcamento -> new OrcamentoDTO(
                            orcamento.getIdOrcamento(),
                            orcamento.getTituloOrcamento(),
                            orcamento.getDescOrcamento(),
                            orcamento.getValorOrcamento(),
                            orcamento.getDataInclusao(),
                            orcamento.getPrevisaoInicio(),
                            orcamento.getDuracaoServico(),
                            orcamento.getFormaPagtoEnum(),
                            orcamento.getNvlUrgenciaEnum(),
                            orcamento.getTipoCategoriaEnum(),
                            orcamento.getStatusOrcamentoEnum()
                    )).collect(Collectors.toList());


            return new PrestadorDTO(prestador, orcamentoDTOS);
        }).collect(Collectors.toList());
    }

    //consultar pela especialidade
    public List<PrestadorDTO> consultarPrestadorPorEspecialidades(String especialidades) {
        List<Prestador> prestadores = prestadorRepository.findByEspecialidadesContaining(especialidades);

        if (prestadores == null) {
            throw new IllegalArgumentException("Nenhum Prestador encontrado!");
        }

        return prestadores.stream().map(prestador -> {
            List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(prestador); // ou .findByPrestadorId(prestador.getId())

            List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                    .map(orcamento -> new OrcamentoDTO(
                            orcamento.getIdOrcamento(),
                            orcamento.getTituloOrcamento(),
                            orcamento.getDescOrcamento(),
                            orcamento.getValorOrcamento(),
                            orcamento.getDataInclusao(),
                            orcamento.getPrevisaoInicio(),
                            orcamento.getDuracaoServico(),
                            orcamento.getFormaPagtoEnum(),
                            orcamento.getNvlUrgenciaEnum(),
                            orcamento.getTipoCategoriaEnum(),
                            orcamento.getStatusOrcamentoEnum()
                    )).collect(Collectors.toList());

            return new PrestadorDTO(prestador, orcamentoDTOS);
        }).collect(Collectors.toList());
    }

    public List<PrestadorDTO> consultarPrestadorPorStatusDisponiblidade(StatusDisponibilidadeEnum status) {
        List<Prestador> prestadores = prestadorRepository.findByStatusDisponibilidade(status);

        if (prestadores == null) {
            throw new IllegalArgumentException("Nenhum Prestador encontrado!");
        }

        return prestadores.stream().map(prestador -> {
            List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(prestador); // ou .findByPrestadorId(prestador.getId())

            List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                    .map(orcamento -> new OrcamentoDTO(
                            orcamento.getIdOrcamento(),
                            orcamento.getTituloOrcamento(),
                            orcamento.getDescOrcamento(),
                            orcamento.getValorOrcamento(),
                            orcamento.getDataInclusao(),
                            orcamento.getPrevisaoInicio(),
                            orcamento.getDuracaoServico(),
                            orcamento.getFormaPagtoEnum(),
                            orcamento.getNvlUrgenciaEnum(),
                            orcamento.getTipoCategoriaEnum(),
                            orcamento.getStatusOrcamentoEnum()
                    )).collect(Collectors.toList());


            return new PrestadorDTO(prestador, orcamentoDTOS);
        }).collect(Collectors.toList());
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
    public PrestadorDTO alterarPrestador(int idUsuario, PrestadorUpdateDTO prestadorUpdateDTO) {
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
        List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(prestadorExistente);

        List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                .map(orcamento -> new OrcamentoDTO(
                        orcamento.getIdOrcamento(),
                        orcamento.getTituloOrcamento(),
                        orcamento.getDescOrcamento(),
                        orcamento.getValorOrcamento(),
                        orcamento.getDataInclusao(),
                        orcamento.getPrevisaoInicio(),
                        orcamento.getDuracaoServico(),
                        orcamento.getFormaPagtoEnum(),
                        orcamento.getNvlUrgenciaEnum(),
                        orcamento.getTipoCategoriaEnum(),
                        orcamento.getStatusOrcamentoEnum()
                )).collect(Collectors.toList());


        //metodo que salva as informaçoes do prestador
        Prestador prestadorAtualizado = prestadorRepository.save(prestadorExistente);
        return new PrestadorDTO(prestadorAtualizado, orcamentoDTOS);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        prestadorRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario
    }

}
