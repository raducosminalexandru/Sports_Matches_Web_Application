package com.example.postgresqlprogram.model;

public class TeamDetails {
    private String teamName;
    private int teamSize;
    private String playerName;
    private String equipmentColor;

    // Getters and setters
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public String getEquipmentColor() { return equipmentColor; }
    public void setEquipmentColor(String equipmentColor) { this.equipmentColor = equipmentColor; }
}
