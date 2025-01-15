package com.example.postgresqlprogram.controller;

import com.example.postgresqlprogram.model.Competition;
import com.example.postgresqlprogram.model.Manager;
import com.example.postgresqlprogram.service.CompetitionService;
import com.example.postgresqlprogram.service.ManagerService;
import com.example.postgresqlprogram.util.PasswordHasher;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/addManager")
    public String addManager(
            @RequestParam String numeManager,
            @RequestParam String prenumeManager,
            @RequestParam String sex,
            @RequestParam String emailManager,
            @RequestParam String parolaManager,
            Model model
    ) {
        if (parolaManager.length() < 10) {
            model.addAttribute("errorMessage", "The password must have at least 10 characters");
            return "authenticatemanager";
        } else if (countNumbers(parolaManager) < 2) {
            model.addAttribute("errorMessage", "The password must have at least 2 numbers");
            return "authenticatemanager";
        }

        String hashedPassword = PasswordHasher.hashPassword(parolaManager);
        Manager manager = new Manager();
        manager.setNumeManager(numeManager);
        manager.setPrenumeManager(prenumeManager);
        manager.setSex(sex);
        manager.setEmailManager(emailManager);
        manager.setParolaManager(hashedPassword);
        managerService.insertManager(manager);
        return "redirect:/succes";
    }


    private int countNumbers(String input) {
        return (int) input.chars()
                .filter(Character::isDigit)
                .count();
    }


    @GetMapping("/succes")
    public String succesPage() {
        return "succes";
    }

    @GetMapping("/manager/managerdashboard")
    public String managerDashboard(HttpSession session, Model model) {
        Long managerId = (Long) session.getAttribute("userId");
        if (managerId == null) {
            return "redirect:/login";
        }

        List<Competition> competitions = competitionService.getCompetitionsByManagerId(managerId);

        model.addAttribute("hasCompetitions", !competitions.isEmpty());
        model.addAttribute("competitions", competitions);

        return "managerdashboard";
    }

    @PostMapping("/updateCompetitionName")
    public String updateCompetitionName(
            @RequestParam Long idCompetition,
            @RequestParam String newCompetitionName,
            HttpSession session
    ) {
        System.out.println("Received ID: " + idCompetition + ", New Name: " + newCompetitionName);
        Long managerId = (Long) session.getAttribute("userId");
        if (managerId == null) {
            return "redirect:/login";
        }

        competitionService.updateCompetitionName(idCompetition, newCompetitionName);
        return "redirect:managerdashboard";
    }


    @PostMapping("/updateCompetitionDate")
    public String updateCompetitionDate(
            @RequestParam Long idCompetition,
            @RequestParam String newCompetitionDate,
            HttpSession session
    ) {
        Long managerId = (Long) session.getAttribute("userId");
        if (managerId == null) {
            return "redirect:/login";
        }

        try {
            String formattedDate = newCompetitionDate.replace("T", " ") + ":00";
            LocalDateTime parsedDate = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            competitionService.updateCompetitionDate(idCompetition, parsedDate);
        } catch (DateTimeParseException e) {
            return "redirect:managerdashboard?error=InvalidDate";
        }

        return "redirect:managerdashboard";
    }

    @GetMapping("/confirmcancel")
    public String confirmCancel(@RequestParam Long idCompetition, HttpSession session, Model model) {
        Long managerId = (Long) session.getAttribute("userId");
        if (managerId == null) {
            return "redirect:/login";
        }

        model.addAttribute("idCompetition", idCompetition);
        return "confirmcancel";
    }

    @PostMapping("/deleteCompetition")
    public String deleteCompetition(@RequestParam Long idCompetition, HttpSession session) {
        Long managerId = (Long) session.getAttribute("userId");
        if (managerId == null) {
            return "redirect:/login";
        }

        competitionService.deleteCompetitionById(idCompetition);
        return "redirect:/manager/managerdashboard";
    }

}
