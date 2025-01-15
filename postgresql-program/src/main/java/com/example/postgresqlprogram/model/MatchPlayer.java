package com.example.postgresqlprogram.model;

public class MatchPlayer {
    private Long idMatch;
    private Long idPlayer;
    private String equipmentColor;

    public Long getIdMatch() { return idMatch; }
    public void setIdMatch(Long idMatch) { this.idMatch = idMatch; }

    public Long getIdPlayer() { return idPlayer; }
    public void setIdPlayer(Long idPlayer) { this.idPlayer = idPlayer; }

    public String getEquipmentColor() { return equipmentColor; }
    public void setEquipmentColor(String equipmentColor) { this.equipmentColor = equipmentColor; }
}
