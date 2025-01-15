package com.example.postgresqlprogram.controller;

import com.example.postgresqlprogram.model.*;
import com.example.postgresqlprogram.service.*;
import com.example.postgresqlprogram.util.PasswordHasher;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private SpectatorService spectatorService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    @PostMapping("/login")
    public String login(@RequestParam String emailManager, @RequestParam String parolaManager, Model model, HttpSession session) {
        String hashedPassword = PasswordHasher.hashPassword(parolaManager);
        Manager manager = managerService.getManagerByEmail(emailManager);
        if (manager != null && manager.getParolaManager().equals(hashedPassword)) {
            session.setAttribute("userRole", "manager");
            session.setAttribute("userId", manager.getIdManager());
            session.setAttribute("userEmail", emailManager);
            return "redirect:/managerdashboard";
        }
        Spectator spectator = spectatorService.getSpectatorsByEmail(emailManager);
        if (spectator != null && spectator.getParolaSpectator().equals(hashedPassword)) {
            session.setAttribute("userRole", "spectator");
            session.setAttribute("userId", spectator.getIdSpectator());
            session.setAttribute("userEmail", emailManager);
            return "redirect:/spectatordashboard";
        }
        Team team = teamService.getTeamByEmail(emailManager);
        if (team != null && team.getParolaEchipa().equals(hashedPassword)) {
            session.setAttribute("userRole", "team");
            session.setAttribute("userId", team.getIdEchipa());
            session.setAttribute("userEmail", emailManager);
            return "redirect:/teamdashboard";
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/managerdashboard")
    public String managerDashboard(HttpSession session, Model model) {
        Long managerId = (Long) session.getAttribute("userId");
        if (managerId == null) {
            return "redirect:/login";
        }

        List<Competition> competitions = competitionService.getCompetitionsByManagerId(managerId);

        if (competitions.isEmpty()) {
            model.addAttribute("message", "You don't have any competitions right now.");
            model.addAttribute("hasCompetitions", false);
        } else {
            model.addAttribute("competitions", competitions);
            model.addAttribute("hasCompetitions", true);
        }

        return "managerdashboard";
    }

    @GetMapping("/spectatordashboard")
    public String spectatorDashboard(HttpSession session, Model model) {
        Long spectatorId = (Long) session.getAttribute("userId");
        if (spectatorId == null) {
            return "redirect:/login";
        }

        List<SpectatorTicketDetails> tickets = matchService.getSpectatorTickets(spectatorId);

        if (tickets.isEmpty()) {
            model.addAttribute("message", "You don't have any tickets.");
        } else {
            model.addAttribute("tickets", tickets);
        }

        return "spectatordashboard";
    }

    @GetMapping("/teamdashboard")
    public String teamDashboard(HttpSession session, Model model) {
        Long teamId = (Long) session.getAttribute("userId");
        if (teamId == null) {
            return "redirect:/login";
        }

        List<TeamDetails> teamDetails = teamService.getTeamDetailsById(teamId);

        if (teamDetails.isEmpty()) {
            model.addAttribute("message", "No players found for your team.");
        } else {
            model.addAttribute("teamDetails", teamDetails);
        }

        return "teamdashboard";
    }
}