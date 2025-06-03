package dev.ghazi.dto;

public record UserRecord(
    String username,
    String name,
    String function,
    int level,
    String department,
    boolean alive
) {

}
