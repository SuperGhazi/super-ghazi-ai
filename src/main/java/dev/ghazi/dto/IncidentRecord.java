package dev.ghazi.dto;

import java.time.LocalDateTime;

public record IncidentRecord(
    UserRecord user,
    String reason,
    LocalDateTime timestamp
) {

}
