package com.example.postgresqlprogram.model;

public class Team {

    private Long idEchipa;
    private String numeEchipa;
    private String telefonEchipa;
    private int lotEchipa;
    private String email_echipa;
    private String parola_echipa;

    public Long getIdEchipa() {
        return idEchipa;
    }

    public void setIdEchipa(Long idEchipa) {
        this.idEchipa = idEchipa;
    }

    public String getNumeEchipa() {
        return numeEchipa;
    }

    public void setNumeEchipa(String numeEchipa) {
        this.numeEchipa = numeEchipa;
    }

    public String getTelefonEchipa() {
        return telefonEchipa;
    }

    public void setTelefonEchipa(String telefonEchipa) {
        this.telefonEchipa = telefonEchipa;
    }

    public int getLotEchipa() {
        return lotEchipa;
    }

    public void setLotEchipa(int lotEchipa) {
        this.lotEchipa = lotEchipa;
    }

    public String getEmailechipa() {
        return email_echipa;
    }

    public void setEmailechipa(String email_echipa) {
        this.email_echipa = email_echipa;
    }

    public String getParolaEchipa() {
        return parola_echipa;
    }

    public void setParolaEchipa(String parola_echipa) {
        this.parola_echipa = parola_echipa;
    }
}
