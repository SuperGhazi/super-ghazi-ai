package dev.ghazi.mapper;

import dev.ghazi.dto.IncidentRecord;
import dev.ghazi.model.Incident;

public class IncidentMapper {

    public static IncidentRecord toIncidentRecord(Incident incident) {
        return new IncidentRecord(
                UserMapper.toUserRecord(incident.getUser()),
                incident.getReason(),
                incident.getTimestamp()
        );
    }
}
