package com.example.postgresqlprogram.controller;

import com.example.postgresqlprogram.service.CompetitionService;
import com.example.postgresqlprogram.service.ManagerService;
import com.example.postgresqlprogram.service.SpectatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    SpectatorService spectatorService;
    @Autowired
    CompetitionService competitionService;
    @Autowired
    ManagerService managerService;

    @GetMapping("/redirectToRegister")
    public String redirectToRegister() {
        return "/registermainpage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/authenticatemanager")
    public String authenticateManagerPage() {
        return "authenticatemanager";
    }

    @GetMapping("/registermainpage")
    public String registermainpagePage() {
        return "registermainpage";
    }

    @GetMapping("/")
    public String getTopStatistics(Model model) {
        List<Map<String, Object>> result = spectatorService.getTopSpectators();
        String mostPopularSport = competitionService.getMostPopularSport();
        String competitionWithMostSponsors = competitionService.getCompetitionWithMostSponsors();
        String managerWithMostCompetitions = managerService.getManagerWithMostCompetitions();
        String cityWithMostCompetitions = competitionService.getCityWithMostCompetitions();
        String competitionWithMostMatches = competitionService.getCompetitionWithMostMatches();
        String sportWithMostSponsors = competitionService.getSportWithMostSponsors();

        model.addAttribute("topSpectators", result);
        model.addAttribute("mostPopularSport", mostPopularSport);
        model.addAttribute("competitionWithMostSponsors", competitionWithMostSponsors);
        model.addAttribute("managerWithMostCompetitions", managerWithMostCompetitions);
        model.addAttribute("cityWithMostCompetitions", cityWithMostCompetitions);
        model.addAttribute("competitionWithMostMatches", competitionWithMostMatches);
        model.addAttribute("sportWithMostSponsors", sportWithMostSponsors);

        return "home";
    }
}


