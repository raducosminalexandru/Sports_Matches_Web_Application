package com.example.postgresqlprogram.model;

public class MatchSpectator {
    private Long idMatch;
    private Long idSpectator;
    private Double ticketPrice;

    public Long getIdMatch() { return idMatch; }
    public void setIdMatch(Long idMatch) { this.idMatch = idMatch; }

    public Long getIdSpectator() { return idSpectator; }
    public void setIdSpectator(Long idSpectator) { this.idSpectator = idSpectator; }

    public Double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(Double ticketPrice) { this.ticketPrice = ticketPrice; }
}

