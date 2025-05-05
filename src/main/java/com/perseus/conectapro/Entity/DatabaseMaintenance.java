package com.perseus.conectapro.Entity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMaintenance {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void removerIndiceUnico() {
        try {
            jdbcTemplate.execute("DROP INDEX UKksul5ocljo5c18haeesn3yrnl ON TBL_ORCAMENTO");
            System.out.println("Índice removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover índice (talvez já tenha sido removido): " + e.getMessage());
        }
    }
}