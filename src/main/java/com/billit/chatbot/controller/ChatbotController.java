package com.billit.chatbot.controller;

import com.billit.chatbot.dto.LoginChatRequest;
import com.billit.chatbot.dto.LoginChatbotOpenRequest;
import com.billit.chatbot.dto.NonLoginChatRequest;
import com.billit.chatbot.dto.ChatResponse;
import com.billit.chatbot.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat") // 엔드포인트 수정 필요
@RequiredArgsConstructor
public class ChatbotController {
    private final ChatbotService chatbotService;

    @PostMapping("/login")
    public ResponseEntity<ChatResponse> loginChat(@RequestBody LoginChatRequest request) {
        ChatResponse response = chatbotService.sendLoginChatRequest(request.getInput());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/non")
    public ResponseEntity<ChatResponse> nonLoginChat(@RequestBody NonLoginChatRequest request) {
        ChatResponse response = chatbotService.sendNonLoginChatRequest(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/open")
    public void openChatbot(@RequestBody LoginChatbotOpenRequest request) {
        chatbotService.openChat(request.getUuid());
    }
}
