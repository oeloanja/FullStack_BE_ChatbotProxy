package com.billit.chatbot.service;

import com.billit.chatbot.dto.LoginChatRequest;
import com.billit.chatbot.dto.NonLoginChatRequest;
import com.billit.chatbot.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    private final WebClient.Builder webClientBuilder;

    @Value("${chatbot.server.url}")
    private String chatbotUrl;

    public ChatResponse sendLoginChatRequest(LoginChatRequest request) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("input", request.getInput());
        formData.add("uuid", request.getUuid());

        return webClientBuilder.build()
                .post()
                .uri(chatbotUrl + "/chat/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();
    }

    public ChatResponse sendNonLoginChatRequest(NonLoginChatRequest request) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("input", request.getInput());

        return webClientBuilder.build()
                .post()
                .uri(chatbotUrl + "/chat/non-login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();
    }
}