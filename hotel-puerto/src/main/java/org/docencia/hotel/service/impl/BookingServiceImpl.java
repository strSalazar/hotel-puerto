package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.mapper.jpa.BookingMapper;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.BookingRepository;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.docencia.hotel.service.api.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final GuestJpaRepository guestRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository,
            RoomRepository roomRepository,
            GuestJpaRepository guestRepository,
            BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        RoomEntity room = roomRepository.findById(booking.getRoomId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada: " + booking.getRoomId()));

        GuestEntity guest = guestRepository.findById(booking.getGuestId())
                .orElseThrow(() -> new RuntimeException("Huésped no encontrado: " + booking.getGuestId()));

        BookingEntity entity = bookingMapper.toEntity(booking);
        entity.setRoom(room);
        entity.setGuest(guest);

        entity = bookingRepository.save(entity);
        return bookingMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        entity.setCheckIn(booking.getCheckIn());
        entity.setCheckOut(booking.getCheckOut());

        if (!entity.getRoom().getId().equals(booking.getRoomId())) {
            RoomEntity newRoom = roomRepository.findById(booking.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
            entity.setRoom(newRoom);
        }

        if (!entity.getGuest().getId().equals(booking.getGuestId())) {
            GuestEntity newGuest = guestRepository.findById(booking.getGuestId())
                    .orElseThrow(() -> new RuntimeException("Huésped no encontrado"));
            entity.setGuest(newGuest);
        }

        return bookingMapper.toDomain(bookingRepository.save(entity));
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Reserva no encontrada con ID: " + id);
        }
        bookingRepository.deleteById(id);
    }
}