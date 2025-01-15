package com.example.postgresqlprogram.controller;

import com.example.postgresqlprogram.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.postgresqlprogram.service.TeamService;
import com.example.postgresqlprogram.util.PasswordHasher;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/addTeam")
    public String addTeam(
            @RequestParam String numeEchipa,
            @RequestParam String telefonEchipa,
            @RequestParam int lotEchipa,
            @RequestParam String emailEchipa,
            @RequestParam String parolaEchipa,
            Model model
    ) {
        if (parolaEchipa.length() < 10) {
            model.addAttribute("errorMessage", "The password must have at least 10 characters");
            return "registerteam";
        } else if (countNumbers(parolaEchipa) < 2) {
            model.addAttribute("errorMessage", "The password must have at least 2 numbers");
            return "registerteam";
        }

        String hashedPassword = PasswordHasher.hashPassword(parolaEchipa);

        Team team = new Team();
        team.setNumeEchipa(numeEchipa);
        team.setTelefonEchipa(telefonEchipa);
        team.setLotEchipa(lotEchipa);
        team.setEmailechipa(emailEchipa);
        team.setParolaEchipa(hashedPassword);

        teamService.insertTeam(team);

        return "teamdashboard";
    }


    private int countNumbers(String input) {
        return (int) input.chars()
                .filter(Character::isDigit)
                .count();
    }

    @GetMapping("/registerteam")
    public String registerteamPage() {
        return "registerteam";
    }
}
