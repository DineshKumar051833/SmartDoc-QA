package org.example.java.smartdoc_qa.AI;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class OllamaClient {

    private final ChatClient chatClient;

    public OllamaClient(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String question) {
        return chatClient
                .prompt()       // Start prompt
                .user(question) // Set user message
                .call()         // Execute the call
                .content();     // Get answer
    }
}