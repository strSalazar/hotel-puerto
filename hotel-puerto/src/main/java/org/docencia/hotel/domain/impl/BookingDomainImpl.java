package org.docencia.hotel.domain.impl;

import java.util.List;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.service.api.BookingService;
import org.springframework.stereotype.Service;

@Service
public class BookingDomainImpl implements BookingDomain {

    private final BookingService service;

    public BookingDomainImpl(BookingService service) {
        this.service = service;
    }

    @Override
    public Booking getBooking(Long id) {
        return service.getBookingById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return service.createBooking(booking);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        return service.updateBooking(id, booking);
    }

    @Override
    public void deleteBooking(Long id) {
        service.deleteBooking(id);
    }
}