package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.*;
import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import com.perseus.conectapro.Repository.EnderecoRepository;
import com.perseus.conectapro.Repository.OrcamentoRepository;
import com.perseus.conectapro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaClienteService {

    private Usuario usuario;
    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;
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

    //cadastrar as informaçoes alem do usuario, faltantes para uma empresa cliente
    public EmpresaCliente cadastrarEmpresaCliente(EmpresaClienteCreateDTO empresaClienteCreateDTO) {

        if (empresaClienteRepository.existsByEmail(empresaClienteCreateDTO.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(empresaClienteCreateDTO.getCep());

        Endereco endereco = new Endereco();

        //Serao definidos após a inserção do cep
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());
        endereco.setCep(viaCep.getCep());

        //Usuario definirá manualmente
        endereco.setNumero(empresaClienteCreateDTO.getNumero());
        endereco.setComplemento(empresaClienteCreateDTO.getComplemento());

        //Persistindo o Endereco
        endereco = enderecoRepository.save(endereco);

        EmpresaCliente empresaCliente = new EmpresaCliente();
        empresaCliente.setCnpj(empresaClienteCreateDTO.getCnpj());
        empresaCliente.setRazaoSocial(empresaClienteCreateDTO.getRazaoSocial());
        empresaCliente.setNome(empresaClienteCreateDTO.getNome());
        empresaCliente.setEmail(empresaClienteCreateDTO.getEmail());
        //Criptografia da senha
        empresaCliente.setSenha(passwordEncoder.encode(empresaClienteCreateDTO.getSenha()));
        empresaCliente.setTelefone(empresaClienteCreateDTO.getTelefone());
        empresaCliente.setTipoUsuario(TipoUsuarioEnum.CLIENTE);
        empresaCliente.setNomeFantasia(empresaClienteCreateDTO.getNomeFantasia());
        empresaCliente.setEndereco(endereco);
        empresaCliente.setCaminhoFoto(empresaClienteCreateDTO.getCaminhoFoto());

        return empresaClienteRepository.save(empresaCliente);
    }

    //consultar somente empresas clientes
    public List<EmpresaClienteDTO> consultarEmpresas(Specification<EmpresaCliente> spec){

        List<EmpresaCliente> clientes = empresaClienteRepository.findAll(spec);
        if (clientes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma empresa encontrada com os filtros fornecidos.");
        }

        return clientes.stream().map(empresaCliente -> {
            List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(empresaCliente);

            List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                    .map(orcamento -> new OrcamentoDTO(
                            orcamento.getIdOrcamento(),
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

            return new EmpresaClienteDTO(empresaCliente, orcamentoDTOS);
        }).collect(Collectors.toList());
    }

    //consultar empresa cliente especifica
    public EmpresaClienteDTO consultarEmpresaEspecifica(int id){

        EmpresaCliente empresaClienteEspecifico = empresaClienteRepository.findByIdUsuario(id);

        if (empresaClienteEspecifico == null) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(empresaClienteEspecifico);

        List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                .map(orcamento -> new OrcamentoDTO(
                        orcamento.getIdOrcamento(),
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


        return new EmpresaClienteDTO(empresaClienteEspecifico, orcamentoDTOS);
    }

    public List<EmpresaClienteDTO> consultarEmpresaPorNome(String nome) {
        List<EmpresaCliente> clientes = empresaClienteRepository.findByNomeContainingIgnoreCase(nome);

        if (clientes == null){
            throw new IllegalArgumentException("Nenhum usuário cliente encontrado!!");
        }

        return clientes.stream().map(empresaCliente -> {
            List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(empresaCliente);

            List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                    .map(orcamento -> new OrcamentoDTO(
                            orcamento.getIdOrcamento(),
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


            return new EmpresaClienteDTO(empresaCliente, orcamentoDTOS);
        }).collect(Collectors.toList());
    }

    // Metodo para validacao das informacoes durante a atualizacao da empresa
    private void validarAtualizacaoEmpresa(EmpresaClienteUpdateDTO dto) {
        if (dto.getCnpj() != null) {
            String cnpjLimpo = dto.getCnpj().replaceAll("\\D", "");
            if (cnpjLimpo.length() != 14) {
                throw new IllegalArgumentException("CNPJ inválido. Deve conter 14 dígitos numéricos");
            }
        }

        if (dto.getRazaoSocial() != null && dto.getRazaoSocial().isBlank()) {
            throw new IllegalArgumentException("A razão social, se informada, não pode estar em branco");
        }

        if (dto.getNomeFantasia() != null && dto.getNomeFantasia().isBlank()) {
            throw new IllegalArgumentException("O nome fantasia, se informado, não pode estar em branco");
        }
    }

    //alterar informaçoes somente da empresa
    public EmpresaClienteDTO alterarEmpresaCliente(int idUsuario, EmpresaClienteUpdateDTO empresaClienteUpdateDTO){
        validarAtualizacaoEmpresa(empresaClienteUpdateDTO);

        EmpresaCliente clienteExistente = empresaClienteRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));

        if(empresaClienteUpdateDTO.getCnpj() != null){
            clienteExistente.setCnpj(empresaClienteUpdateDTO.getCnpj());
        }
        if(empresaClienteUpdateDTO.getRazaoSocial() != null){
            clienteExistente.setRazaoSocial(empresaClienteUpdateDTO.getRazaoSocial());
        }
        if(empresaClienteUpdateDTO.getNomeFantasia() != null){
            clienteExistente.setNomeFantasia(empresaClienteUpdateDTO.getNomeFantasia());
        }
        List<Orcamento> orcamentos = orcamentoRepository.findByIdUsuario(clienteExistente);

        List<OrcamentoDTO> orcamentoDTOS = orcamentos.stream()
                .map(orcamento -> new OrcamentoDTO(
                        orcamento.getIdOrcamento(),
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
        EmpresaCliente clienteAtualizado = empresaClienteRepository.save(clienteExistente);
        return new EmpresaClienteDTO(clienteAtualizado, orcamentoDTOS);

    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        empresaClienteRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario

    }
}
