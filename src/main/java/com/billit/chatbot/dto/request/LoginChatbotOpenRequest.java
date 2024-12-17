package com.billit.chatbot.dto.request;

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

    @JsonProperty("user_pn")
    private String user_pn;
}
