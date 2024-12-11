package com.billit.chatbot.service;

import com.billit.chatbot.dto.request.LoginChatRequest;
import com.billit.chatbot.dto.request.LoginChatbotOpenRequest;
import com.billit.chatbot.dto.request.NonLoginChatRequest;
import com.billit.chatbot.dto.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatbotService {
    private final WebClient.Builder webClientBuilder;

    @Value("${chatbot.server.url}")
    private String chatbotUrl;

    @Value("${chatbotnon.server.url}")
    private String chatbotNonUrl;

    public void openChat(String uuid) {
        if (uuid == null || uuid.trim().isEmpty()) {
            throw new IllegalArgumentException("UUID cannot be null or empty");
        }
        LoginChatbotOpenRequest request = new LoginChatbotOpenRequest(uuid);
        log.info("Sending request to chatbot: {}", request);

        webClientBuilder.build()
                .post()
                .uri(chatbotUrl + "/chat/open")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public ChatResponse sendLoginChatRequest(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("input cannot be null or empty");
        }
        LoginChatRequest request = new LoginChatRequest(input);
        return webClientBuilder.build()
                .post()
                .uri(chatbotUrl + "/chat/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();
    }

    public ChatResponse sendNonLoginChatRequest(NonLoginChatRequest request) {
        return webClientBuilder.build()
                .post()
                .uri(chatbotNonUrl + "/chat/non")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();
    }
}