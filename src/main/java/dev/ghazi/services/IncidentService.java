package dev.ghazi.services;

import java.util.List;

import com.vaadin.hilla.Endpoint;

import dev.ghazi.dto.IncidentRecord;
import dev.ghazi.mapper.IncidentMapper;
import dev.ghazi.repository.IncidentRepository;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

@Endpoint
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    @PermitAll
    public List<IncidentRecord> findAllIncidents() {
        return incidentRepository.findAll()
                .stream()
                .map(incident -> IncidentMapper.toIncidentRecord(incident))
                .toList();
    }
}
