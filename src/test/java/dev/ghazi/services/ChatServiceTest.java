package dev.ghazi.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    ChatService chatService;

    @Test
    void testChat() {
        chatService.chat("Hello, who are you?")
            .doOnNext(response -> System.out.println("Response: " + response))
            .doOnError(error -> System.err.println("Error: " + error.getMessage()))
            .blockLast();
    }
}
