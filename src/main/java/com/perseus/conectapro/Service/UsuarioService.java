package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.UsuarioCreateDTO;
import com.perseus.conectapro.DTO.UsuarioUpdateDTO;
import com.perseus.conectapro.DTO.ViaCepDTO;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Entity.Enuns.RoleEnum;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.EnderecoRepository;
import com.perseus.conectapro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;          //dependencia do repository que faz conexao com o banco
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ViaCepService viaCepService;

    //consultar usuarios
    public List<Usuario> consultarUsuarios(){
        return usuarioRepository.findAll();                //chama o metodo da biblioteca jparepository pre-definido pela framework
    }

    // Expressão regular para validar e-mails
    // permite que antes do @ tenha letras e numeros, depois dominio e por fim extensao limitada
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    //metodo que faz validacao do email para verificar se sintaxe esta correta
    public boolean isEmailValido(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Usuario cadastrarUsuario(UsuarioCreateDTO usuarioCreateDTO) {

        Usuario usuarioExiste = usuarioRepository.findByEmail(usuarioCreateDTO.getEmail());

        if (usuarioExiste != null){
            throw new RuntimeException("Usuário já existe!!");
        }

        ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(usuarioCreateDTO.getCep());

        // Criação do usuário
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCreateDTO.getNome());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setTelefone(usuarioCreateDTO.getTelefone());
        usuario.setTipoUsuario(usuarioCreateDTO.getTipoUsuario());
        usuario.setCaminhoFoto(usuarioCreateDTO.getCaminhoFoto());
        usuario.setRole(usuarioCreateDTO.getRole());

        //Criptografia da senha
        usuario.setSenha(passwordEncoder.encode(usuarioCreateDTO.getSenha()));

        // Criação do endereço com base nos dados do DTO
        Endereco endereco = new Endereco();
        //Serao definidos após a inserção do cep
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());
        endereco.setCep(viaCep.getCep());

        //Usuario definirá manualmente
        endereco.setNumero(usuarioCreateDTO.getNumero());
        endereco.setComplemento(usuarioCreateDTO.getComplemento());

        // Salva o novo endereço no banco
        endereco = enderecoRepository.save(endereco);

        // Associa o endereço ao usuário
        usuario.setEndereco(endereco);

        // Salva o novo usuário no banco de dados
        return usuarioRepository.save(usuario);
    }

    //Metodo para validacao das informacoes inseridas durante a atualizacao do usuario
    private void validarAtualizacaoUsuario(UsuarioUpdateDTO dto) {
        if (dto.getNome() != null && dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome, se informado, não pode estar em branco");
        }

        if (dto.getEmail() != null) {
            if (dto.getEmail().isBlank()) {
                throw new IllegalArgumentException("O email, se informado, não pode estar em branco");
            }
            if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new IllegalArgumentException("Formato de e-mail inválido");
            }
        }

        if (dto.getSenha() != null && dto.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha, se informada, deve ter pelo menos 6 caracteres");
        }

        if (dto.getTelefone() != null && !dto.getTelefone().matches("^\\d{10,11}$")) {
            throw new IllegalArgumentException("Telefone inválido. Deve conter apenas números (10 ou 11 dígitos)");
        }

        if (dto.getTipoUsuario() != null && dto.getTipoUsuario().name().isBlank()) {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }
    }


    //alterar informaçoes do usuario
    public Usuario alterarUsuario(int idUsuario, UsuarioUpdateDTO usuarioUpdateDTO){
        validarAtualizacaoUsuario(usuarioUpdateDTO);

        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!!"));

        if(usuarioUpdateDTO.getNome() != null){
            usuarioExistente.setNome(usuarioUpdateDTO.getNome());
        }
        if(usuarioUpdateDTO.getSenha() != null){
            //Criptografia da senha durante alteração de senha
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioUpdateDTO.getSenha()));
        }
        if(usuarioUpdateDTO.getTelefone() != null){
            usuarioExistente.setTelefone(usuarioUpdateDTO.getTelefone());
        }
        if(usuarioUpdateDTO.getCaminhoFoto() != null){
            usuarioExistente.setCaminhoFoto(usuarioUpdateDTO.getCaminhoFoto());
        }
        if(usuarioUpdateDTO.getTipoUsuario() != null){
            usuarioExistente.setTipoUsuario(usuarioUpdateDTO.getTipoUsuario());
        }

        if(usuarioUpdateDTO.getRole() != null){
            usuarioExistente.setRole(usuarioUpdateDTO.getRole());
        }

        //retornou um metodo que salva as informações no banco
        return usuarioRepository.save(usuarioExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        usuarioRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario

    }

    public List<Usuario> consultarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    //consultar usuario especifico
    public Usuario consultarUsuarioPorId(int idUsuario) {
        Usuario usuarioEspecifico = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));

        return usuarioEspecifico;
    }
}
