package com.example.chatai.controller;

import com.example.chatai.service.OpenAiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ChatController {
  private final OpenAiService openAiService;

  public ChatController(OpenAiService openAiService) {
    this.openAiService = openAiService;
  }

  @GetMapping("/chat")
  public Mono<String> chat(@RequestParam String message) {
    return openAiService.askChatGPT(message)
        .onErrorResume(e -> {
          // Handle error and return a default message
          return Mono.just("Error occurred: " + e.getMessage());
        });
  }
}
