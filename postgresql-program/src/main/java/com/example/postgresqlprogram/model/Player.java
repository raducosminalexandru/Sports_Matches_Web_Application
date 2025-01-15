package com.example.postgresqlprogram.model;

public class Player {

    private Long idJucator;
    private String numeJucator;

    public Long getIdJucator() {
        return idJucator;
    }

    public void setIdJucator(Long idJucator) {
        this.idJucator = idJucator;
    }

    public String getNumeJucator() {
        return numeJucator;
    }

    public void setNumeJucator(String numeJucator) {
        this.numeJucator = numeJucator;
    }

    public String getPrenumeJucator() {
        return prenumeJucator;
    }

    public void setPrenumeJucator(String prenumeJucator) {
        this.prenumeJucator = prenumeJucator;
    }

    public Long getIdEchipa() {
        return idEchipa;
    }

    public void setIdEchipa(Long idEchipa) {
        this.idEchipa = idEchipa;
    }

    private String prenumeJucator;
    private Long idEchipa;
}
