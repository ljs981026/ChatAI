package com.example.chatai.service;

import com.example.chatai.dto.OpenAi;
import com.example.chatai.dto.OpenAi.openAiRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenAiService {

  private final WebClient webClient;

  public OpenAiService(@Value("${openai.api-key}") String apiKey) {
    this.webClient = WebClient.builder()
        .baseUrl("https://api.openai.com/v1")
        .defaultHeader("Authorization", "Bearer " + apiKey)
        .build();
  }

  public Mono<String> askChatGPT(String userMessage) {
    OpenAi.openAiRequest request = new openAiRequest(
        "gpt-3.5-turbo",
        List.of(
            new OpenAi.openAiRequest.Message("system", "당신은 친절한 AI 비서입니다."),
            new OpenAi.openAiRequest.Message("user", userMessage)
        )
    );

    return webClient.post()
        .uri("/chat/completions")
        .bodyValue(request)
        .retrieve()
        .bodyToMono(OpenAi.OpenAiResponse.class)
        .map(response -> response.getChoices().get(0).getMessage().getContent());
  }
}
