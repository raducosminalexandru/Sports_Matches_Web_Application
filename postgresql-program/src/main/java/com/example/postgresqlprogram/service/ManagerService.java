package com.example.postgresqlprogram.service;

import com.example.postgresqlprogram.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Manager> managerRowMapper = (rs, rowNum) -> {
        Manager manager = new Manager();
        manager.setIdManager(rs.getLong("id_manager"));
        manager.setNumeManager(rs.getString("nume_manager"));
        manager.setPrenumeManager(rs.getString("prenume_manager"));
        manager.setSex(rs.getString("sex"));
        manager.setEmailManager(rs.getString("email_manager"));
        manager.setParolaManager(rs.getString("parola_manager"));
        return manager;
    };

    public Manager getManagerByEmail(String emailManager) {
        String sql = "SELECT * FROM manageri WHERE email_manager = ?";
        try {
            return jdbcTemplate.queryForObject(sql, managerRowMapper, emailManager);
        } catch (Exception e) {
            return null;
        }
    }

    public void insertManager(Manager manager) {
        String sql = "INSERT INTO manageri (nume_manager, prenume_manager, sex, email_manager, parola_manager) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                manager.getNumeManager(),
                manager.getPrenumeManager(),
                manager.getSex(),
                manager.getEmailManager(),
                manager.getParolaManager());
    }

    public String getManagerWithMostCompetitions() {
        String sql = """
                SELECT m.nume_manager || ' ' || m.prenume_manager AS full_name 
                FROM manageri m 
                JOIN (
                SELECT id_manager, COUNT(*) AS num_competitii 
                FROM competitii 
                GROUP BY id_manager 
                ORDER BY num_competitii DESC 
                LIMIT 1
            ) c 
            ON m.id_manager = c.id_manager
        """;


        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
