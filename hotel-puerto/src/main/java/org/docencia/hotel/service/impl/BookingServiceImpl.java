package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * crea una nueva reserva asociando un huesped a una habitacion
     * 
     * @param booking la reserva a crear
     * @return Booking
     */
    @Override
    @Transactional
    public Booking createBooking(Booking booking) {
        RoomEntity room = roomRepository.findById(booking.getRoomId())
                .orElseThrow(() -> new RuntimeException("habitacion no encontrada: " + booking.getRoomId()));

        GuestEntity guest = guestRepository.findById(booking.getGuestId())
                .orElseThrow(() -> new RuntimeException("huesped no encontrado: " + booking.getGuestId()));

        BookingEntity entity = bookingMapper.toEntity(booking);
        entity.setRoom(room);
        entity.setGuest(guest);

        entity = bookingRepository.save(entity);
        return bookingMapper.toDomain(entity);
    }

    /**
     * actualiza una reserva existente incluyendo habitacion, huesped y fechas
     * 
     * @param id el id de la reserva a actualizar
     * @param booking los nuevos datos de la reserva
     * @return Booking
     */
    @Override
    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        BookingEntity entity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("reserva no encontrada con id: " + id));

        entity.setCheckIn(booking.getCheckIn());
        entity.setCheckOut(booking.getCheckOut());

        if (!entity.getRoom().getId().equals(booking.getRoomId())) {
            RoomEntity newRoom = roomRepository.findById(booking.getRoomId())
                    .orElseThrow(() -> new RuntimeException("habitacion no encontrada"));
            entity.setRoom(newRoom);
        }

        if (!entity.getGuest().getId().equals(booking.getGuestId())) {
            GuestEntity newGuest = guestRepository.findById(booking.getGuestId())
                    .orElseThrow(() -> new RuntimeException("huesped no encontrado"));
            entity.setGuest(newGuest);
        }

        return bookingMapper.toDomain(bookingRepository.save(entity));
    }

    /**
     * obtiene una reserva por su id
     * 
     * @param id el id de la reserva
     * @return Booking
     */
    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("reserva no encontrada con id: " + id));
    }

    /**
     * obtiene todas las reservas del sistema
     * 
     * @return List
     */
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * elimina una reserva del sistema
     * 
     * @param id el id de la reserva a eliminar
     */
    @Override
    @Transactional
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("reserva no encontrada con id: " + id);
        }
        bookingRepository.deleteById(id);
    }
}