package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.mapper.jpa.BookingMapper;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.docencia.hotel.persistence.repository.jpa.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository repository;
    @Mock
    private BookingMapper mapper;

    @InjectMocks
    private BookingServiceImpl service;

    @Test
    void createBooking_ShouldSaveAndReturnBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        BookingEntity entity = new BookingEntity();
        entity.setId(1L);

        when(mapper.toEntity(booking)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(booking);

        Booking result = service.createBooking(booking);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void getBookingById_ShouldReturnBooking_WhenFound() {
        Long id = 1L;
        BookingEntity entity = new BookingEntity();
        entity.setId(id);
        Booking booking = new Booking();
        booking.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(booking);

        Booking result = service.getBookingById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}