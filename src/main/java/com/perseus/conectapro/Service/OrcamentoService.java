package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.OrcamentoCreateDTO;
import com.perseus.conectapro.DTO.OrcamentoDTO;
import com.perseus.conectapro.DTO.OrcamentoUpdateDTO;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Orcamento;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Usuario;
import com.perseus.conectapro.Repository.EmpresaClienteRepository;
import com.perseus.conectapro.Repository.OrcamentoRepository;
import com.perseus.conectapro.Repository.PrestadorRepository;
import com.perseus.conectapro.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public OrcamentoDTO cadastrarOrcamento(OrcamentoCreateDTO orcamentoCreateDTO)
    {
        Orcamento orcamento = new Orcamento();
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }


        if (usuario instanceof EmpresaCliente) {
            EmpresaCliente empresaCliente = (EmpresaCliente) usuario;
            orcamento.setIdEmpresaCliente(empresaCliente);
        }
        else if (usuario instanceof Prestador) {
            Prestador prestador = (Prestador) usuario;
            orcamento.setIdPrestador(prestador);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário inválido.");
        }

        orcamento.setIdUsuario(usuario); //<- satifaz o a busca por usuario
        orcamento.setDataInclusao(orcamentoCreateDTO.getDataInclusao());
        orcamento.setPrevisaoInicio(orcamentoCreateDTO.getPrevisaoInicio());
        orcamento.setDuracaoServico(orcamentoCreateDTO.getDuracaoServico());
        orcamento.setValorOrcamento(orcamentoCreateDTO.getValorOrcamento());
        orcamento.setFormaPagtoEnum(orcamentoCreateDTO.getFormaPagtoEnum());
        orcamento.setNvlUrgenciaEnum(orcamentoCreateDTO.getNvlUrgenciaEnum());
        orcamento.setTipoCategoriaEnum(orcamentoCreateDTO.getTipoCategoriaEnum());
        orcamento.setStatusOrcamentoEnum(orcamentoCreateDTO.getStatusOrcamentoEnum());

        Orcamento orcamentoCriado = orcamentoRepository.save(orcamento);
        return new OrcamentoDTO(orcamentoCriado);
    }

    public List<OrcamentoDTO> consultarOrcamentos(){
        List<Orcamento> orcamentos = orcamentoRepository.findAll();
        if (orcamentos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return orcamentos.stream()
                .map(OrcamentoDTO::new)
                .collect(Collectors.toList());
    }

    public Orcamento alterarOrcamento(int idOrcamento, OrcamentoUpdateDTO orcamentoUpdateDTO){

        Orcamento orcamentoExistente = orcamentoRepository.findById(idOrcamento)
                .orElseThrow(() -> new IllegalArgumentException("Orçamento não encontrado!!"));

        if(orcamentoUpdateDTO.getValorOrcamento() !=null){
            orcamentoExistente.setValorOrcamento(orcamentoUpdateDTO.getValorOrcamento());
        }
        if(orcamentoUpdateDTO.getPrevisaoInicio() !=null){
            orcamentoExistente.setPrevisaoInicio(orcamentoUpdateDTO.getPrevisaoInicio());
        }
        if(orcamentoUpdateDTO.getDuracaoServico() != 0){
            orcamentoExistente.setDuracaoServico(orcamentoUpdateDTO.getDuracaoServico());
        }
        if(orcamentoUpdateDTO.getFormaPagtoEnum() !=null){
            orcamentoExistente.setFormaPagtoEnum(orcamentoUpdateDTO.getFormaPagtoEnum());
        }
        if(orcamentoUpdateDTO.getNvlUrgenciaEnum() !=null){
            orcamentoExistente.setNvlUrgenciaEnum(orcamentoUpdateDTO.getNvlUrgenciaEnum());
        }
        if(orcamentoUpdateDTO.getTipoCategoriaEnum() !=null){
            orcamentoExistente.setTipoCategoriaEnum(orcamentoUpdateDTO.getTipoCategoriaEnum());
        }
        return orcamentoRepository.save(orcamentoExistente);
    }

    public OrcamentoDTO consultarOrcamentoUnico(int id){
        Orcamento orcamentoEspecifico = orcamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Nenhum orçamento encontrado com essas informações,!")));
        return new OrcamentoDTO(orcamentoEspecifico);
    }

    public List<OrcamentoDTO> consultarOrcamentoPorIdUsuario(Usuario usuario) {
        return orcamentoRepository.findByIdUsuario(usuario)
                .stream()
                .map(OrcamentoDTO::new)
                .collect(Collectors.toList());
    }

    public void delete(int idOrcamento) {
        orcamentoRepository.deleteById(idOrcamento);
    }
}
