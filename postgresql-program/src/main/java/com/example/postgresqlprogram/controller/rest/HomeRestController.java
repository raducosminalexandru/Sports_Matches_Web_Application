package com.example.postgresqlprogram.controller.rest;

import com.example.postgresqlprogram.service.CompetitionService;
import com.example.postgresqlprogram.service.ManagerService;
import com.example.postgresqlprogram.service.SpectatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeRestController {

    @Autowired
    private SpectatorService spectatorService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public String home() {
        return "API Rest Controller";
    }

    @GetMapping("/top-spectators")
    public List<Map<String, Object>> getTopSpectators() {
        return spectatorService.getTopSpectators();
    }

    @GetMapping("/most-popular-sport")
    public String getMostPopularSport() {
        return competitionService.getMostPopularSport();
    }

    @GetMapping("/competition-with-most-sponsors")
    public String getCompetitionWithMostSponsors() {
        return competitionService.getCompetitionWithMostSponsors();
    }

    @GetMapping("/manager-with-most-competitions")
    public String getManagerWithMostCompetitions() {
        return managerService.getManagerWithMostCompetitions();
    }
}
