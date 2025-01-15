package com.example.postgresqlprogram.model;

import java.time.LocalDateTime;

public class Spectator {
    private Long idSpectator;
    private String numeSpectator;
    private String prenumeSpectator;
    private char sex;
    private String metodaPlata;
    private String emailSpectator;
    private String parolaSpectator;

    public Long getIdSpectator() {
        return idSpectator;
    }

    public void setIdSpectator(Long idSpectator) {
        this.idSpectator = idSpectator;
    }

    public String getNumeSpectator() {
        return numeSpectator;
    }

    public void setNumeSpectator(String numeSpectator) {
        this.numeSpectator = numeSpectator;
    }

    public String getPrenumeSpectator() {
        return prenumeSpectator;
    }

    public void setPrenumeSpectator(String prenumeSpectator) {
        this.prenumeSpectator = prenumeSpectator;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        this.metodaPlata = metodaPlata;
    }

    public String getEmailSpectator() {
        return emailSpectator;
    }

    public void setEmailSpectator(String emailSpectator) {
        this.emailSpectator = emailSpectator;
    }

    public String getParolaSpectator() {
        return parolaSpectator;
    }

    public void setParolaSpectator(String parolaSpectator) {
        this.parolaSpectator = parolaSpectator;
    }
}


