package dev.ghazi.services;

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
        return assistant.chat(message);
    }
}
