package com.example.postgresqlprogram.model;

public class Sponsor {
    private Long idSponsor;
    private String sponsorName;
    private String sponsorEmail;
    private String sponsorPhone;
    private Double allocatedAmount;

    public Long getIdSponsor() { return idSponsor; }
    public void setIdSponsor(Long idSponsor) { this.idSponsor = idSponsor; }

    public String getSponsorName() { return sponsorName; }
    public void setSponsorName(String sponsorName) { this.sponsorName = sponsorName; }

    public String getSponsorEmail() { return sponsorEmail; }
    public void setSponsorEmail(String sponsorEmail) { this.sponsorEmail = sponsorEmail; }

    public String getSponsorPhone() { return sponsorPhone; }
    public void setSponsorPhone(String sponsorPhone) { this.sponsorPhone = sponsorPhone; }

    public Double getAllocatedAmount() { return allocatedAmount; }
    public void setAllocatedAmount(Double allocatedAmount) { this.allocatedAmount = allocatedAmount; }
}
