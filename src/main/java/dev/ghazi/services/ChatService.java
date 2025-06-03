package dev.ghazi.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.hilla.Endpoint;

import jakarta.annotation.security.PermitAll;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Endpoint
@RequiredArgsConstructor
public class ChatService {

    private final Assistant assistant;

    @PermitAll
    public Flux<@NonNull String> chat(String message) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return assistant.chat(username, message);
    }
}
