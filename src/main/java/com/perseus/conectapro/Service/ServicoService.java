package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.ServicoCreateDTO;
import com.perseus.conectapro.DTO.ServicoUpdateDTO;
import com.perseus.conectapro.DTO.ViaCepDTO;
import com.perseus.conectapro.Entity.*;
import com.perseus.conectapro.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private SegmentoRepository segmentoRepository;
    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    private PrestadorRepository prestadorRepository;

    //cadastrar servico utilizando jpa repository
    public Servico cadastrarServico(ServicoCreateDTO dto) {

        EmpresaCliente empresa = empresaClienteRepository.findById(dto.getIdEmpresaCliente())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Segmento segmento = segmentoRepository.findById(dto.getIdSegmento())
                .orElseThrow(() -> new RuntimeException("Segmento não encontrado"));

        // Buscar dados do CEP na API ViaCep
        ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(dto.getCep());

        // Criar e salvar o endereço
        Endereco endereco = new Endereco();
        endereco.setCep(viaCep.getCep());
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getLocalidade());
        endereco.setUf(viaCep.getUf());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco = enderecoRepository.save(endereco);

        // Criar entidade Servico
        Servico servico = new Servico();
        servico.setEmpresaCliente(empresa);
        servico.setSegmento(segmento);
        servico.setValorContratacao(dto.getValorContratacao());
        servico.setDataInclusao(dto.getDataInclusao() != null ? dto.getDataInclusao() : LocalDateTime.now());
        servico.setSituacaoServico(dto.getSituacaoServico());
        servico.setTitulo(dto.getTitulo());
        servico.setDescricao(dto.getDescricao());
        servico.setEndereco(endereco);
        servico.setFormaPagtoEnum(dto.getFormaPagto());
        servico.setNvlUrgenciaEnum(dto.getNvlUrgencia());

        return servicoRepository.save(servico);
    }


    //consultar servicos
    public List<Servico> consultarServicos(){
        return servicoRepository.findAll();
    }

    //alterar informações do usuario
    public Servico alterarServico(int idServico, ServicoUpdateDTO dto) {
        Servico servicoExistente = servicoRepository.findById(idServico)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (dto.getIdPrestador() != null) {
            Prestador prestador = prestadorRepository.findById(dto.getIdPrestador())
                    .orElseThrow(() -> new RuntimeException("Prestador não encontrado"));
            servicoExistente.setPrestador(prestador);
        }

        if (dto.getSituacaoServico() != null) {
            servicoExistente.setSituacaoServico(dto.getSituacaoServico());
        }

        if (dto.getValorContratacao() != null) {
            servicoExistente.setValorContratacao(dto.getValorContratacao());
        }

        if (dto.getTitulo() != null) {
            servicoExistente.setTitulo(dto.getTitulo());
        }

        if (dto.getDescricao() != null) {
            servicoExistente.setDescricao(dto.getDescricao());
        }

        if (dto.getFormaPagto() != null) {
            servicoExistente.setFormaPagtoEnum(dto.getFormaPagto());
        }

        if (dto.getNvlUrgencia() != null) {
            servicoExistente.setNvlUrgenciaEnum(dto.getNvlUrgencia());
        }

        if (dto.getDataAprovacao() != null) {
            servicoExistente.setDataAprovacao(dto.getDataAprovacao());
        }

        if (dto.getDataExecucao() != null) {
            servicoExistente.setDataExecucao(dto.getDataExecucao());
        }

        if (dto.getDataPagamento() != null) {
            servicoExistente.setDataPagamento(dto.getDataPagamento());
        }

        if (dto.getCep() != null || dto.getNumero() != null || dto.getComplemento() != null) {
            Endereco endereco = servicoExistente.getEndereco();

            if (endereco == null) {
                endereco = new Endereco();
            }

            if (dto.getCep() != null) {
                ViaCepDTO viaCep = viaCepService.buscarEnderecoPorCep(dto.getCep());
                endereco.setCep(viaCep.getCep());
                endereco.setLogradouro(viaCep.getLogradouro());
                endereco.setBairro(viaCep.getBairro());
                endereco.setCidade(viaCep.getLocalidade());
                endereco.setUf(viaCep.getUf());
            }

            if (dto.getNumero() != null) {
                endereco.setNumero(dto.getNumero());
            }

            if (dto.getComplemento() != null) {
                endereco.setComplemento(dto.getComplemento());
            }

            endereco = enderecoRepository.save(endereco);
            servicoExistente.setEndereco(endereco);
        }


        return servicoRepository.save(servicoExistente);
    }


    //deletar servico
    public void delete(int idServico){
        servicoRepository.deleteById(idServico);
    }

    public Servico consultarServicoPorId(int idServico) {
        Servico servicoEspecifico = servicoRepository.findById(idServico)
                .orElseThrow(() -> new IllegalArgumentException("Servico não encontrado!!"));

        return servicoEspecifico;
    }



}
