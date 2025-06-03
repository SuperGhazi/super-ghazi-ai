package dev.ghazi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ghazi.model.Incident;
import dev.ghazi.model.User;
import dev.ghazi.repository.IncidentRepository;
import dev.ghazi.repository.UserRepository;
import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Tools {

    private final UserRepository userRepository;
    private final IncidentRepository incidentRepository;

    @Tool("Retrives complete information about the user")
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));
    }

    @Tool("Retrieves all users")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Tool("Reports an incident with a reason")
    public void reportIncident(String username, String reason) {
        log.warn("Reporting incident for user {}: {}", username, reason);
        userRepository.findByUsername(username)
            .map(u -> createIncident(u, reason))
            .ifPresent(incidentRepository::save);
    }

    private Incident createIncident(User user, String reason) {
        var incident = new Incident();
        incident.setUser(user);
        incident.setReason(reason);
        incident.setTimestamp(LocalDateTime.now());
        return incident;
    }

    @Tool("Retrieves all incidents")
    public List<Incident> findAllIncidents() {
        return incidentRepository.findAll();
    }
}
