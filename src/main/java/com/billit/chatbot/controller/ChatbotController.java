package com.billit.chatbot.controller;

import com.billit.chatbot.dto.LoginChatRequest;
import com.billit.chatbot.dto.NonLoginChatRequest;
import com.billit.chatbot.dto.ChatResponse;
import com.billit.chatbot.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat") // 엔드포인트 수정 필요
@RequiredArgsConstructor
public class ChatbotController {
    private final ChatbotService chatbotService;

    @PostMapping("/login") // 엔드포인트 수정 필요
    public ResponseEntity<ChatResponse> loginChat(@RequestBody LoginChatRequest request) {
        ChatResponse response = chatbotService.sendLoginChatRequest(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/non-login") // 엔드포인트 수정 필요
    public ResponseEntity<ChatResponse> nonLoginChat(@RequestBody NonLoginChatRequest request) {
        ChatResponse response = chatbotService.sendNonLoginChatRequest(request);
        return ResponseEntity.ok(response);
    }
}
