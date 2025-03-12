package com.perseus.conectapro.Service;

import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Endereco;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;



@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;           //dependencia do repository que faz conexao com o banco

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

    //cadastro do usuario com metodo jpa
    public Usuario cadastrarUsuario(Usuario usuario){
        if (!isEmailValido(usuario.getEmail())) {
            System.out.println("Erro: E-mail inválido.");
            return usuarioRepository.save(usuario);                ////chama o metodo da biblioteca jparepository pre-definido pela framework
        }

        return usuario;
    }

    //alterar informaçoes do usuario
    public Usuario alterarUsuario(int idUsuario){
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!!"));

        //alterando todos atributos da classe
        usuarioExistente.setDocumento(usuarioExistente.getDocumento());
        usuarioExistente.setNome(usuarioExistente.getNome());
        usuarioExistente.setEmail(usuarioExistente.getEmail());
        usuarioExistente.setSenha(usuarioExistente.getSenha());
        usuarioExistente.setTelefone(usuarioExistente.getTelefone());
        usuarioExistente.setCaminhoFoto(usuarioExistente.getCaminhoFoto());
        usuarioExistente.setTipoUsuario(usuarioExistente.getTipoUsuario());

        //retornou um metodo que salva as informações no banco
        return usuarioRepository.save(usuarioExistente);
    }

    //deletar usuario pelo id
    public void delete(int idUsuario){
        usuarioRepository.deleteById(idUsuario);           //metodo que faz o delete do usuario

    }

    public List<Usuario> consultarUsuarioPorNome(String nome) {
        return usuarioRepository.findByName(nome);
    }

    //consultar usuario especifico
    public Usuario consultarUsuarioPorId(int idUsuario) {
        Usuario usuarioEspecifico = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado!!"));

        return usuarioEspecifico;
    }

//    @Autowired
//    private EnderecoRepository enderecoRepository;
//
//    public EmpresaCliente cadastrarCliente(EmpresaCliente cliente) {
//        // Salvar o Cliente no banco para gerar um ID (se necessário)
//        EmpresaCliente clienteSalvo = empresaClienteRepository.save(cliente);
//
//        // Criar e preencher o Endereço
//        Endereco endereco = new Endereco();
//        endereco.setRua(cliente.getEnderecos().get(0).getRua());  // Pegando do objeto recebido
//        endereco.setBairro(cliente.getEnderecos().get(0).getBairro());
//        endereco.setComplemento(cliente.getEnderecos().get(0).getComplemento());
//        endereco.setNumero(cliente.getEnderecos().get(0).getNumero());
//        endereco.setCidade(cliente.getEnderecos().get(0).getCidade());
//        endereco.setEstado(cliente.getEnderecos().get(0).getEstado());
//
//        // Associar o endereço ao cliente
//        endereco.setUsuario(clienteSalvo);  // Definir o cliente ao endereço
//        enderecoRepository.save(endereco); // Salvar endereço no banco
//
//        // Relacionar o cliente ao endereço
//        clienteSalvo.getEnderecos().add(endereco);
//
//        // Atualizar o Cliente no banco para garantir consistência
//        return empresaClienteRepository.save(clienteSalvo);
//    }

}
