package com.yootiful.service;


import com.yootiful.entities.Booking;
import com.yootiful.mapper.BookingMapper;
import com.yootiful.models.BookingRecord;

import java.util.function.Function;

public class BookingFunction implements Function<BookingFunction.Request, BookingRecord> {
    private final BookingService bookingService;

    public BookingFunction(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public BookingRecord apply(Request request) {
        Booking booking = bookingService.getBookingByIdAndFirstNameAndLastName(request.id(), request.firstName(), request.lastName());
        return BookingMapper.toRecord(booking);
    }

    public record Request(Long id, String firstName, String lastName) {
    }
}
