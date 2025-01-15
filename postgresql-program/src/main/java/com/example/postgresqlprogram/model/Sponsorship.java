package com.example.postgresqlprogram.model;

public class Sponsorship {
    private Long idCompetition;
    private Long idSponsor;
    private String sponsorshipType;

    public Long getIdCompetition() { return idCompetition; }
    public void setIdCompetition(Long idCompetition) { this.idCompetition = idCompetition; }

    public Long getIdSponsor() { return idSponsor; }
    public void setIdSponsor(Long idSponsor) { this.idSponsor = idSponsor; }

    public String getSponsorshipType() { return sponsorshipType; }
    public void setSponsorshipType(String sponsorshipType) { this.sponsorshipType = sponsorshipType; }
}
