package com.example.postgresqlprogram.controller;

import com.example.postgresqlprogram.model.Spectator;
import com.example.postgresqlprogram.model.SpectatorTicketDetails;
import com.example.postgresqlprogram.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.postgresqlprogram.service.SpectatorService;
import com.example.postgresqlprogram.util.PasswordHasher;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SpectatorController {

    @Autowired
    private SpectatorService spectatorService;

    @Autowired
    private MatchService matchService;

    @PostMapping("/addSpectator")
    public String addSpectator(
            @RequestParam String numeSpectator,
            @RequestParam String prenumeSpectator,
            @RequestParam String sex,
            @RequestParam String metodaPlata,
            @RequestParam String emailSpectator,
            @RequestParam String parolaSpectator,
            Model model
    ) {
        if (parolaSpectator.length() < 10) {
            model.addAttribute("errorMessage", "The password must have at least 10 characters");
            return "registerspectator";
        } else if (countNumbers(parolaSpectator) < 2) {
            model.addAttribute("errorMessage", "The password must have at least 2 numbers");
            return "registerspectator";
        }

        String hashedPassword = PasswordHasher.hashPassword(parolaSpectator);

        Spectator spectator = new Spectator();
        spectator.setNumeSpectator(numeSpectator);
        spectator.setPrenumeSpectator(prenumeSpectator);
        spectator.setSex(sex.charAt(0));
        spectator.setMetodaPlata(metodaPlata);
        spectator.setEmailSpectator(emailSpectator);
        spectator.setParolaSpectator(hashedPassword);

        spectatorService.insertSpectator(spectator);

        return "/spectatordashboard";
    }

    private int countNumbers(String input) {
        return (int) input.chars()
                .filter(Character::isDigit)
                .count();
    }

    @GetMapping("/registerspectator")
    public String registerspectatorPage() {
        return "registerspectator";
    }

    @PostMapping("/deleteTicket")
    public String deleteTicket(@RequestParam Long spectatorId, @RequestParam Long matchId) {
        spectatorService.deleteSpectatorTicket(spectatorId, matchId);
        return "redirect:/spectatordashboard";
    }

    @GetMapping("/confirmDeletePage")
    public String confirmDeletePage(
            @RequestParam Long spectatorId,
            @RequestParam Long matchId,
            Model model
    ) {
        List<SpectatorTicketDetails> tickets = matchService.getSpectatorTicketByMatch(spectatorId, matchId);

        model.addAttribute("tickets", tickets);
        model.addAttribute("spectatorId", spectatorId);

        return "confirmdelete";
    }



    @PostMapping("/confirmDelete")
    public String confirmDelete(
            @RequestParam Long spectatorId,
            @RequestParam Long matchId,
            @RequestParam String confirm
    ) {
        if ("yes".equals(confirm)) {
            spectatorService.deleteSpectatorTicket(spectatorId, matchId);
        }
        return "redirect:/spectatordashboard";
    }
}
