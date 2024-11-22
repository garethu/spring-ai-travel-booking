package com.yootiful.models;

import java.time.LocalDate;

public record BookingRecord(
        Long id,
        String firstName,
        String lastName,
        LocalDate travelDate,
        String travelFrom,
        String travelTo,
        Character status,
        String bookingClass
) {
}
