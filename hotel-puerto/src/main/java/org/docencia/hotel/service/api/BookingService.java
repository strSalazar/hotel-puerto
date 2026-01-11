package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Long id, Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    void deleteBooking(Long id);
}