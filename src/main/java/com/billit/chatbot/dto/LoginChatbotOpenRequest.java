package com.billit.chatbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginChatbotOpenRequest {
    @JsonProperty("uuid")
    private String uuid;
}
