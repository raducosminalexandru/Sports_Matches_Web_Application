package com.example.postgresqlprogram.service;

import com.example.postgresqlprogram.model.Spectator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpectatorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Spectator> spectatorRowMapper = (rs, rowNum) -> {
        Spectator spectator = new Spectator();
        spectator.setIdSpectator(rs.getLong("id_spectator"));
        spectator.setNumeSpectator(rs.getString("nume_spectator"));
        spectator.setPrenumeSpectator(rs.getString("prenume_spectator"));
        spectator.setSex(rs.getString("sex").charAt(0));
        spectator.setMetodaPlata(rs.getString("metoda_plata"));
        spectator.setEmailSpectator(rs.getString("email_spectator"));
        spectator.setParolaSpectator(rs.getString("parola_spectator"));
        return spectator;
    };

    public Spectator getSpectatorsByEmail(String emailAddress) {
        String sql = "SELECT * FROM spectatori WHERE email_spectator = ?";

        List<Spectator> spectators = jdbcTemplate.query(sql, new Object[]{emailAddress}, (rs, rowNum) -> {
            Spectator spectator = new Spectator();
            spectator.setIdSpectator(rs.getLong("id_spectator"));
            spectator.setNumeSpectator(rs.getString("nume_spectator"));
            spectator.setPrenumeSpectator(rs.getString("prenume_spectator"));
            spectator.setSex(rs.getString("sex").charAt(0));
            spectator.setMetodaPlata(rs.getString("metoda_plata"));
            spectator.setEmailSpectator(rs.getString("email_spectator"));
            spectator.setParolaSpectator(rs.getString("parola_spectator"));
            return spectator;
        });

        return spectators.isEmpty() ? null : spectators.get(0);
    }

    public void insertSpectator(Spectator spectator) {
        String sql = "INSERT INTO spectatori (nume_spectator, prenume_spectator, sex, metoda_plata, email_spectator, parola_spectator) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                spectator.getNumeSpectator(),
                spectator.getPrenumeSpectator(),
                String.valueOf(spectator.getSex()),
                spectator.getMetodaPlata(),
                spectator.getEmailSpectator(),
                spectator.getParolaSpectator());
    }

    public List<Map<String, Object>> getTopSpectators() {
        String query = """
        SELECT s.nume_spectator, s.prenume_spectator, COUNT(ms.id_meci) AS nr_meciuri
        FROM meciurispectatori ms
        JOIN spectatori s ON ms.id_spectator = s.id_spectator
        GROUP BY s.id_spectator, s.nume_spectator, s.prenume_spectator
        HAVING COUNT(ms.id_meci) = (
            SELECT MAX(nr_meciuri)
            FROM (
                SELECT COUNT(ms1.id_meci) AS nr_meciuri
                FROM meciurispectatori ms1
                GROUP BY ms1.id_spectator
            )
        ) LIMIT 1
        """;
        return jdbcTemplate.queryForList(query);
    }

    public void deleteSpectatorTicket(Long spectatorId, Long matchId) {
        String sql = """
            DELETE FROM meciurispectatori
            WHERE id_spectator = ? AND id_meci = ?;
            """;
        jdbcTemplate.update(sql, spectatorId, matchId);
    }
}

