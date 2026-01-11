package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.service.api.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingDomainImplTest {

    @Mock
    private BookingService service;

    @InjectMocks
    private BookingDomainImpl domain;

    @Test
    void createBookingTest() {
        Booking booking = new Booking();
        when(service.createBooking(booking)).thenReturn(booking);

        Booking result = domain.createBooking(booking);

        assertEquals(booking, result);
        verify(service).createBooking(booking);
    }

    @Test
    void getBookingTest() {
        Long id = 1L;
        Booking booking = new Booking();
        when(service.getBookingById(id)).thenReturn(booking);

        Booking result = domain.getBooking(id);

        assertEquals(booking, result);
        verify(service).getBookingById(id);
    }

    @Test
    void getAllBookingsTest() {
        List<Booking> list = Collections.emptyList();
        when(service.getAllBookings()).thenReturn(list);

        List<Booking> result = domain.getAllBookings();

        assertEquals(list, result);
        verify(service).getAllBookings();
    }

    @Test
    void updateBookingTest() {
        Long id = 1L;
        Booking booking = new Booking();
        when(service.updateBooking(id, booking)).thenReturn(booking);

        Booking result = domain.updateBooking(id, booking);

        assertEquals(booking, result);
        verify(service).updateBooking(id, booking);
    }

    @Test
    void deleteBookingTest() {
        Long id = 1L;

        domain.deleteBooking(id);

        verify(service).deleteBooking(id);
    }
}