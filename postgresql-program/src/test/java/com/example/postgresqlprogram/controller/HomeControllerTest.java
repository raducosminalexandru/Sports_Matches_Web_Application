package com.example.postgresqlprogram.controller;

import com.example.postgresqlprogram.service.CompetitionService;
import com.example.postgresqlprogram.service.ManagerService;
import com.example.postgresqlprogram.service.SpectatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SpectatorService spectatorService;

    @Mock
    private CompetitionService competitionService;

    @Mock
    private ManagerService managerService;

    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        homeController = new HomeController();
        homeController.spectatorService = spectatorService;
        homeController.competitionService = competitionService;
        homeController.managerService = managerService;
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    void getTopSpectator() throws Exception {
        List<Map<String, Object>> mockedSpectators = Collections.singletonList(
                Map.of("name", "John Doe", "points", 1000)
        );
        when(spectatorService.getTopSpectators()).thenReturn(mockedSpectators);

        when(competitionService.getMostPopularSport()).thenReturn("Football");
        when(competitionService.getCompetitionWithMostSponsors()).thenReturn("World Cup");
        when(managerService.getManagerWithMostCompetitions()).thenReturn("Alice Smith");

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("topSpectators", mockedSpectators))
                .andExpect(model().attribute("mostPopularSport", "Football"))
                .andExpect(model().attribute("competitionWithMostSponsors", "World Cup"))
                .andExpect(model().attribute("managerWithMostCompetitions", "Alice Smith"));
    }
}
