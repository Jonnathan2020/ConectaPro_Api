package com.perseus.conectapro.Service;

import com.perseus.conectapro.Entity.Plano;
import com.perseus.conectapro.Repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    //Cadastrar plano
    public Plano cadastrarPlano(Plano plano) { return planoRepository.save(plano); }

    //Consultar planos
    public List<Plano> listarPlanos() {
        return planoRepository.findAll();
    }

    //Buscar plano por id
    public Plano buscarPlanoPorId(Integer id) {
        Plano planoExistente = planoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado!!"));

        return planoExistente;
    }

    //Alterar plano
    public Plano alterarPlano(Integer id, Plano plano) {
        Plano planoExistente = planoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado!!"));

        if (plano.getDescPlano() != null) {
            planoExistente.setDescPlano(plano.getDescPlano());
        }
        if (plano.getValorFixoPlano() != null) {
            planoExistente.setValorFixoPlano(plano.getValorFixoPlano());
        }
        if (plano.getPercentualPlano() != null) {
            planoExistente.setPercentualPlano(plano.getPercentualPlano());
        }

        return planoRepository.save(planoExistente);

    }

    //Deletar plano
    public void deletarPlano(Integer id) {
         planoRepository.deleteById(id);
    }

}
