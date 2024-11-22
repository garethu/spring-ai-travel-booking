package com.yootiful.service;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingAssistantService {
    private final OllamaChatModel chatModel;

    public BookingAssistantService(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }
     public String answer(String text) {
       /* SystemMessage systemMessage = new SystemMessage("""
            You are a customer chat support agent of an airline named "Funnair".
                                    Respond in a friendly, helpful, and joyful manner.
                                    You are interacting with customers through an online chat system.
                                    If they ask for their booking details and they have entered in the booking id, their first name and last name, then provide the booking details.
                                    Before providing information about a booking or cancelling a booking, you MUST always
                                    get the following information from the user: booking number, customer first name and last name.
                                    Check the message history for this information before asking the user.
                                    Before changing a booking you MUST ensure it is permitted by the terms.
                                    If there is a charge for the change, you MUST ask the user to consent before proceeding.
                                    Use the provided functions to fetch booking details, change bookings, and cancel bookings.
                                    Use parallel function calling if required.
                                """);*/

         SystemMessage systemMessage = new SystemMessage("""
            You are a customer chat support agent of an train booking system.
                                    Respond in a friendly, helpful, and joyful manner.
                                    You are interacting with customers through an online chat system.
                                    If they ask for their booking details and they have entered in the booking id, their first name and last name, then provide the booking details.
                                    If you cannot find the booking details, tell them that you cannot provide the information with the details provided.
                                    If they would like to cancel their booking, cancel the booking provided you have confirmed their booking details with a yes or no response.
                                """);

        UserMessage userMessage = new UserMessage(text);

        return getChatResponse(List.of(systemMessage, userMessage))
                .getResult().getOutput().getContent();
    }

    private ChatResponse getChatResponse(List<Message> messages) {
        return chatModel.call(
                new Prompt(
                        messages,
                        OllamaOptions.builder()
                                .withModel("llama3.2")
                                .withFunction("getBookingDetails")
                                .withFunction("cancelBooking")
                                .build()
                )
        );
    }
}
