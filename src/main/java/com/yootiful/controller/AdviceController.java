package com.yootiful.controller;

import com.yootiful.models.BookingRecord;
import com.yootiful.service.AdviceService;
import com.yootiful.service.BookingAssistantService;
import com.yootiful.models.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class AdviceController {
    private final AdviceService adviceService;
    private final BookingAssistantService bookingAssistantService;

    public AdviceController(AdviceService adviceService, BookingAssistantService bookingAssistantService) {
        this.adviceService = adviceService;
        this.bookingAssistantService = bookingAssistantService;
    }

    @PostMapping("/advice/question")
    public ResponseEntity<String> generate(@RequestBody Question question) {
        return ResponseEntity.ok(adviceService.answer(question.text()));
    }

    @PostMapping("/booking/question")
    public ResponseEntity<String> generateBookingResponse(@RequestBody Question question) {
        System.out.println("getting data for request: " + question.text());
        String booking = bookingAssistantService.answer(question.text());
        return ResponseEntity.ok(booking);
    }
}
