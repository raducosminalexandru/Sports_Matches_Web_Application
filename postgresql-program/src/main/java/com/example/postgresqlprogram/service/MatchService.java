package com.example.postgresqlprogram.service;

import com.example.postgresqlprogram.model.SpectatorTicketDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<SpectatorTicketDetails> spectatorTicketDetailsRowMapper = (rs, rowNum) -> {
        SpectatorTicketDetails details = new SpectatorTicketDetails();
        details.setSpectatorId(rs.getLong("spectatorId"));
        details.setMatchId(rs.getLong("matchId"));
        details.setSpectatorName(rs.getString("spectatorName"));
        details.setTicketPrice(rs.getDouble("ticketPrice"));
        details.setMatchDate(rs.getObject("matchDate", LocalDateTime.class));
        return details;
    };

    public List<SpectatorTicketDetails> getSpectatorTickets(Long spectatorId) {
        String sql = """
            SELECT 
                s.id_spectator AS spectatorId,
                m.id_meci AS matchId,
                s.nume_spectator AS spectatorName, 
                ms.pret_bilet AS ticketPrice, 
                m.data_sustinerii AS matchDate
            FROM 
                spectatori s 
            JOIN 
                meciurispectatori ms ON s.id_spectator = ms.id_spectator 
            JOIN 
                meciuri m ON ms.id_meci = m.id_meci 
            WHERE 
                s.id_spectator = ?;
            """;

        return jdbcTemplate.query(sql, spectatorTicketDetailsRowMapper, spectatorId);
    }

    public List<SpectatorTicketDetails> getSpectatorTicketByMatch(Long spectatorId, Long matchId) {
        String sql = """
        SELECT 
            s.id_spectator AS spectatorId,
            m.id_meci AS matchId,
            s.nume_spectator AS spectatorName, 
            ms.pret_bilet AS ticketPrice, 
            m.data_sustinerii AS matchDate
        FROM 
            spectatori s 
        JOIN 
            meciurispectatori ms ON s.id_spectator = ms.id_spectator 
        JOIN 
            meciuri m ON ms.id_meci = m.id_meci 
        WHERE 
            s.id_spectator = ? AND m.id_meci = ?;
        """;

        return jdbcTemplate.query(sql, spectatorTicketDetailsRowMapper, spectatorId, matchId);
    }
}
