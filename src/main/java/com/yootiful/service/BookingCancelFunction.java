package com.yootiful.service;


import com.yootiful.entities.Booking;
import com.yootiful.mapper.BookingMapper;
import com.yootiful.models.BookingRecord;

import java.util.function.Function;

public class BookingCancelFunction implements Function<BookingCancelFunction.Request, BookingRecord> {
    private final BookingService bookingService;

    public BookingCancelFunction(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public BookingRecord apply(Request request) {
        Booking booking = bookingService.deleteBooking(request.id());
        return BookingMapper.toRecord(booking);
    }

    public record Request(Long id) {
    }
}
