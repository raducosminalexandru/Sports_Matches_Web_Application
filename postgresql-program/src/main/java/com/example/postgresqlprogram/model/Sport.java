package com.example.postgresqlprogram.model;

public class Sport {
    private Long idSport;
    private String sportName;
    private Boolean isContactSport;
    private Integer gameDuration;

    public Long getIdSport() { return idSport; }
    public void setIdSport(Long idSport) { this.idSport = idSport; }

    public String getSportName() { return sportName; }
    public void setSportName(String sportName) { this.sportName = sportName; }

    public Boolean getIsContactSport() { return isContactSport; }
    public void setIsContactSport(Boolean isContactSport) { this.isContactSport = isContactSport; }

    public Integer getGameDuration() { return gameDuration; }
    public void setGameDuration(Integer gameDuration) { this.gameDuration = gameDuration; }
}
