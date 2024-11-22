package com.yootiful.repository;


import com.yootiful.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByIdAndFirstNameAndLastName(Long id, String firstName, String lastName);

}
