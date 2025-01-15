package com.example.postgresqlprogram.model;

import java.time.LocalDateTime;

public class Competition {
    private Long idCompetition;
    private String competitionName;
    private LocalDateTime competitionDate;
    private Long idLocation;
    private Long idSport;
    private Long idManager;
    private String sportName;
    private String locationName;

    public Long getIdCompetition() { return idCompetition; }
    public void setIdCompetition(Long idCompetition) { this.idCompetition = idCompetition; }

    public String getCompetitionName() { return competitionName; }
    public void setCompetitionName(String competitionName) { this.competitionName = competitionName; }

    public LocalDateTime getCompetitionDate() { return competitionDate; }
    public void setCompetitionDate(LocalDateTime competitionDate) { this.competitionDate = competitionDate; }

    public Long getIdLocation() { return idLocation; }
    public void setIdLocation(Long idLocation) { this.idLocation = idLocation; }

    public Long getIdSport() { return idSport; }
    public void setIdSport(Long idSport) { this.idSport = idSport; }

    public Long getIdManager() { return idManager; }
    public void setIdManager(Long idManager) { this.idManager = idManager; }

    public String getSportName() { return sportName; }
    public void setSportName(String sportName) { this.sportName = sportName; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
}
