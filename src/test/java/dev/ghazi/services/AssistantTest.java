package dev.ghazi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssistantTest {

    @Autowired
    Assistant assistant;

    @Test
    void testChat() {
        assistant.chat("b.holland", "Hello, who are you?")
            .doOnNext(response -> System.out.println("Response: " + response))
            .doOnError(error -> System.err.println("Error: " + error.getMessage()))
            .blockLast();
    }
}
