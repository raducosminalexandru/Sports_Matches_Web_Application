package com.example.postgresqlprogram.model;

import java.time.LocalDateTime;

public class Match {
    private Long idMatch;
    private Integer seatCount;
    private LocalDateTime matchDate;
    private String refereeFirstName;
    private String refereeLastName;
    private Long idCompetition;

    public Long getIdMatch() { return idMatch; }
    public void setIdMatch(Long idMatch) { this.idMatch = idMatch; }

    public Integer getSeatCount() { return seatCount; }
    public void setSeatCount(Integer seatCount) { this.seatCount = seatCount; }

    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }

    public String getRefereeFirstName() { return refereeFirstName; }
    public void setRefereeFirstName(String refereeFirstName) { this.refereeFirstName = refereeFirstName; }

    public String getRefereeLastName() { return refereeLastName; }
    public void setRefereeLastName(String refereeLastName) { this.refereeLastName = refereeLastName; }

    public Long getIdCompetition() { return idCompetition; }
    public void setIdCompetition(Long idCompetition) { this.idCompetition = idCompetition; }
}

