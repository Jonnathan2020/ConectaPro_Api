package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.EmpresaClienteCreateDTO;
import com.perseus.conectapro.DTO.EmpresaClienteUpdateDTO;
import com.perseus.conectapro.DTO.ViaCepDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import com.perseus.conectapro.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<EmpresaCliente> consultarEmpresaPorNome(String nome) {
        return empresaClienteRepository.findByNomeContainingIgnoreCase(nome);
    }

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
    public List<EmpresaCliente> consultarEmpresasCliente(){

        return empresaClienteRepository.findByTipoUsuario("CLIENTE");
    }

    //consultar empresa cliente especifica
    public EmpresaCliente consultarEmpresaEspecifica(int idUsuario){
        EmpresaCliente empresaClienteEspecifica = empresaClienteRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!!"));

        return empresaClienteEspecifica;
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
    public EmpresaCliente alterarEmpresaCliente(int idUsuario, EmpresaClienteUpdateDTO empresaClienteUpdateDTO){
       validarAtualizacaoEmpresa(empresaClienteUpdateDTO);

        EmpresaCliente empresaExistente = empresaClienteRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!!"));

        if(empresaClienteUpdateDTO.getCnpj() != null){
            empresaExistente.setCnpj(empresaClienteUpdateDTO.getCnpj());
        }
        if(empresaClienteUpdateDTO.getRazaoSocial() != null){
            empresaExistente.setRazaoSocial(empresaClienteUpdateDTO.getRazaoSocial());
        }
        if(empresaClienteUpdateDTO.getNomeFantasia() != null){
            empresaExistente.setNomeFantasia(empresaClienteUpdateDTO.getNomeFantasia());
        }

        //metodo que salva as informaçoes do prestador
        return empresaClienteRepository.save(empresaExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        empresaClienteRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario

    }
}
