package com.yootiful.mapper;

import com.yootiful.entities.Booking;
import com.yootiful.models.BookingRecord;

public class BookingMapper {

    public static BookingRecord toRecord(Booking booking) {
        return new BookingRecord(
                booking.getId(),
                booking.getFirstName(),
                booking.getLastName(),
                booking.getTravelDate(),
                booking.getTravelFrom(),
                booking.getTravelTo(),
                booking.getStatus(),
                booking.getBookingClass()
        );
    }
}
