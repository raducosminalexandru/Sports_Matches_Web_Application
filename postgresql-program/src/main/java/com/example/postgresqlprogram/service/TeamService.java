package com.example.postgresqlprogram.service;

import com.example.postgresqlprogram.model.Team;
import com.example.postgresqlprogram.model.TeamDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Team> teamRowMapper = (rs, rowNum) -> {
        Team team = new Team();
        team.setIdEchipa(rs.getLong("id_echipa"));
        team.setNumeEchipa(rs.getString("nume_echipa"));
        team.setTelefonEchipa(rs.getString("telefon_contact"));
        team.setLotEchipa(rs.getInt("lot_echipa"));
        team.setEmailechipa(rs.getString("email_echipa"));
        team.setParolaEchipa(rs.getString("parola_echipa"));
        return team;
    };

    private RowMapper<TeamDetails> teamDetailsRowMapper = new RowMapper<>() {
        @Override
        public TeamDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            TeamDetails teamDetails = new TeamDetails();
            teamDetails.setTeamName(rs.getString("team_name"));
            teamDetails.setTeamSize(rs.getInt("team_size"));
            teamDetails.setPlayerName(rs.getString("player_name"));
            teamDetails.setEquipmentColor(rs.getString("equipment_color"));
            return teamDetails;
        }
    };

    public Team getTeamByEmail(String emailEchipa) {
        String sql = "SELECT * FROM echipe WHERE email_echipa = ?";
        try {
            return jdbcTemplate.queryForObject(sql, teamRowMapper, emailEchipa);
        } catch (Exception e) {
            return null;
        }
    }

    public void insertTeam(Team team) {
        String sql = "INSERT INTO echipe (nume_echipa, telefon_contact, lot_echipa, email_echipa, parola_echipa) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                team.getNumeEchipa(),
                team.getTelefonEchipa(),
                team.getLotEchipa(),
                team.getEmailechipa(),
                team.getParolaEchipa()
        );
    }

    public List<TeamDetails> getTeamDetailsById(Long teamId) {
        String sql = """
            SELECT 
                e.nume_echipa AS team_name,
                e.lot_echipa AS team_size,
                j.nume_jucator AS player_name,
                mp.culoare_echipament AS equipment_color
            FROM 
                echipe e
            LEFT JOIN 
                jucatori j ON e.id_echipa = j.id_echipa
            LEFT JOIN 
                meciurijucatori mp ON j.id_jucator = mp.id_jucator
            WHERE 
                e.id_echipa = ?
            """;

        return jdbcTemplate.query(sql, teamDetailsRowMapper, teamId);
    }

    public void updateTeamName(Long teamId, String newTeamName) {
        String sql = "UPDATE echipe SET nume_echipa = ? WHERE id_echipa = ?";
        jdbcTemplate.update(sql, newTeamName, teamId);
    }
}
