package com.perseus.conectapro.Service;

import com.perseus.conectapro.DTO.ViaCepDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ViaCepDTO buscarEnderecoPorCep(String cep) {

        // Remove qualquer caractere que não seja número
        cep = cep.replaceAll("[^\\d]", "");

        if (cep.length() != 8) {
            throw new IllegalArgumentException("O CEP deve conter exatamente 8 dígitos");
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ViaCepDTO resposta = restTemplate.getForObject(url, ViaCepDTO.class);

        if (resposta == null || Boolean.TRUE.equals(resposta.getErro())) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado");
        }

        return resposta;
    }
}
