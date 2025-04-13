package com.example.chatai.dto;

import com.example.chatai.dto.OpenAi.openAiRequest.Message;
import java.awt.Choice;
import java.util.List;
import lombok.Getter;

public class OpenAi {
  @Getter
  public static class openAiRequest {
    private String model;
    private List<Message> messages;

    public openAiRequest(String model, List<Message> messages) {
      this.model = model;
      this.messages = messages;
    }

    @Getter
    public static class Message {
      private String role;
      private String content;

      public Message(String role, String content) {
        this.role = role;
        this.content = content;
      }
    }
  }

  @Getter
  public static class OpenAiResponse {
    private List<Choice> choices;

    @Getter
    public static class Choice {
      private Message message;
    }

    @Getter
    public static class Message {
      private String role;
      private String content;
    }
  }


}
