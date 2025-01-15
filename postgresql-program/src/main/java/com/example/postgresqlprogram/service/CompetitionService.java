package com.example.postgresqlprogram.service;

import com.example.postgresqlprogram.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompetitionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Competition> competitionRowMapper = (rs, rowNum) -> {
        Competition competition = new Competition();
        competition.setIdCompetition(rs.getLong("id_competitie"));
        competition.setCompetitionName(rs.getString("nume_competitie"));
        competition.setCompetitionDate(rs.getTimestamp("data_competitie").toLocalDateTime());
        competition.setIdLocation(rs.getLong("id_locatie"));
        competition.setIdSport(rs.getLong("id_sport"));
        competition.setIdManager(rs.getLong("id_manager"));
        competition.setLocationName(rs.getString("nume_oras"));
        competition.setSportName(rs.getString("nume_sport"));
        return competition;
    };

    public List<Competition> getCompetitionsByManagerId(Long managerId) {
        String sql = """
            SELECT c.id_competitie, 
                   c.nume_competitie, 
                   c.data_competitie, 
                   c.id_locatie, 
                   c.id_sport, 
                   c.id_manager, 
                   (SELECT nume_oras 
                    FROM (SELECT l.nume_oras 
                          FROM locatii l 
                          WHERE l.id_locatie = c.id_locatie) AS location_subquery) AS nume_oras, 
                   (SELECT nume_sport 
                    FROM (SELECT s.nume_sport 
                          FROM sporturi s 
                          WHERE s.id_sport = c.id_sport) AS sport_subquery) AS nume_sport
            FROM competitii c
            WHERE c.id_manager = ?
            ORDER BY c.id_competitie ASC
            """;
        return jdbcTemplate.query(sql, competitionRowMapper, managerId);
    }

    /* String sql = """
                SELECT c.id_competitie, c.nume_competitie, c.data_competitie,
                       c.id_locatie, c.id_sport, c.id_manager,
                       l.nume_oras, s.nume_sport
                FROM competitii c
                JOIN locatii l ON c.id_locatie = l.id_locatie
                JOIN sporturi s ON c.id_sport = s.id_sport
                WHERE c.id_manager = ?
                ORDER BY c.id_competitie ASC
                """;
     */

    public String getMostPopularSport() {
        String sql = """
            SELECT s.nume_sport
            FROM sporturi s
            WHERE s.id_sport = (
                SELECT id_sport
                FROM (
                    SELECT id_sport, COUNT(*) AS competition_count
                    FROM competitii
                    GROUP BY id_sport
                ) subquery
                WHERE competition_count = (
                    SELECT MAX(competition_count)
                    FROM (
                        SELECT COUNT(*) AS competition_count
                        FROM competitii
                        GROUP BY id_sport
                    ) inner_subquery
                )
            )
            LIMIT 1
            """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public String getCompetitionWithMostSponsors() {
        String sql = """
        SELECT c.nume_competitie
        FROM competitii c
        WHERE c.id_competitie = (
            SELECT id_competitie
            FROM (
                SELECT id_competitie, COUNT(*) AS sponsor_count
                FROM sponsorizari
                GROUP BY id_competitie
            ) subquery
            WHERE sponsor_count = (
                SELECT MAX(sponsor_count)
                FROM (
                    SELECT COUNT(*) AS sponsor_count
                    FROM sponsorizari
                    GROUP BY id_competitie
                ) inner_subquery
            )
        )
        LIMIT 1
        """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public void updateCompetitionName(Long idCompetition, String newCompetitionName) {
        String sql = "UPDATE competitii SET nume_competitie = ? WHERE id_competitie = ?";
        jdbcTemplate.update(sql, newCompetitionName, idCompetition);
    }

    public void updateCompetitionDate(Long idCompetition, LocalDateTime newCompetitionDate) {
        String sql = "UPDATE competitii SET data_competitie = ? WHERE id_competitie = ?";
        jdbcTemplate.update(sql, newCompetitionDate, idCompetition);
    }

    public String getCityWithMostCompetitions() {
        String sql = """
        SELECT l.nume_oras
        FROM locatii l
        WHERE l.id_locatie = (
            SELECT id_locatie
            FROM (
                SELECT id_locatie, COUNT(*) AS competition_count
                FROM competitii
                GROUP BY id_locatie
            ) subquery
            WHERE competition_count = (
                SELECT MAX(competition_count)
                FROM (
                    SELECT COUNT(*) AS competition_count
                    FROM competitii
                    GROUP BY id_locatie
                ) inner_subquery
            )
        )
        LIMIT 1
        """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public String getCompetitionWithMostMatches() {
        String sql = """
        SELECT c.nume_competitie
        FROM competitii c
        JOIN meciuri m ON c.id_competitie = m.id_competitie
        GROUP BY c.id_competitie, c.nume_competitie
        ORDER BY COUNT(m.id_meci) DESC
        LIMIT 1
        """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public String getSportWithMostSponsors() {
        String sql = """
        SELECT s.nume_sport
        FROM sporturi s
        JOIN competitii c ON s.id_sport = c.id_sport
        JOIN sponsorizari sp ON c.id_competitie = sp.id_competitie
        GROUP BY s.id_sport, s.nume_sport
        ORDER BY COUNT(sp.id_sponsor) DESC
        LIMIT 1
        """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public void deleteCompetitionById(Long idCompetition) {
        String sql = "DELETE FROM competitii WHERE id_competitie = ?";
        jdbcTemplate.update(sql, idCompetition);
    }

}
