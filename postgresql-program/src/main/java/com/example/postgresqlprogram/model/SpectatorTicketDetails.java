package com.example.postgresqlprogram.model;

import java.time.LocalDateTime;

public class SpectatorTicketDetails {
    private Long spectatorId;
    private Long matchId;
    private String spectatorName;
    private double ticketPrice;
    private LocalDateTime matchDate;

    // Getters and setters
    public Long getSpectatorId() {
        return spectatorId;
    }

    public void setSpectatorId(Long spectatorId) {
        this.spectatorId = spectatorId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getSpectatorName() {
        return spectatorName;
    }

    public void setSpectatorName(String spectatorName) {
        this.spectatorName = spectatorName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }
}
