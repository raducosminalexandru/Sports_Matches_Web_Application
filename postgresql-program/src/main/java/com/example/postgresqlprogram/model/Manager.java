package com.example.postgresqlprogram.model;


public class Manager {
    private Long idManager;
    private String numeManager;
    private String prenumeManager;
    private String sex;
    private String emailManager;
    private String parolaManager;


    public Long getIdManager() { return idManager; }
    public void setIdManager(Long idManager) { this.idManager = idManager; }

    public String getNumeManager() { return numeManager; }
    public void setNumeManager(String numeManager) { this.numeManager = numeManager; }

    public String getPrenumeManager() { return prenumeManager; }
    public void setPrenumeManager(String prenumeManager) { this.prenumeManager = prenumeManager; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getEmailManager() { return emailManager; }
    public void setEmailManager(String emailManager) { this.emailManager = emailManager; }

    public String getParolaManager() { return parolaManager; }
    public void setParolaManager(String parolaManager) { this.parolaManager = parolaManager; }
}
