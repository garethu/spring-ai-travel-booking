package com.yootiful.service;

import com.yootiful.entities.Booking;
import com.yootiful.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }
    public Booking getBookingByIdAndFirstNameAndLastName(Long id, String firstName, String lastName) {
        Booking booking = bookingRepository.findByIdAndFirstNameAndLastName(id, firstName, lastName);
        if (booking == null) {
            System.out.println("Booking service: Booking not found with id: " + id);
            return null;
        } else {
            System.out.println("Booking found with id: " + id);
            return booking;
        }
    }


    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id);

        existingBooking.setFirstName(updatedBooking.getFirstName());
        existingBooking.setLastName(updatedBooking.getLastName());
        existingBooking.setTravelDate(updatedBooking.getTravelDate());
        existingBooking.setTravelFrom(updatedBooking.getTravelFrom());
        existingBooking.setTravelTo(updatedBooking.getTravelTo());
        existingBooking.setStatus(updatedBooking.getStatus());
        existingBooking.setBookingClass(updatedBooking.getBookingClass());

        return bookingRepository.save(existingBooking);
    }

    public Booking deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
        return booking;
    }
}
