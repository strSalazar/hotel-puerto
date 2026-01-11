package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    @Transactional
    public Room createRoom(Room room) {
        HotelEntity hotel = hotelRepository.findById(room.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + room.getHotelId()));

        RoomEntity entity = roomMapper.toEntity(room);
        entity.setHotel(hotel);

        entity = roomRepository.save(entity);
        return roomMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Room updateRoom(Long id, Room room) {
        RoomEntity entity = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));

        entity.setNumber(room.getNumber());
        entity.setType(room.getType());
        entity.setPricePerNight(room.getPricePerNight());

        if (!entity.getHotel().getId().equals(room.getHotelId())) {
            HotelEntity newHotel = hotelRepository.findById(room.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + room.getHotelId()));
            entity.setHotel(newHotel);
        }

        return roomMapper.toDomain(roomRepository.save(entity));
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada con ID: " + id));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Habitación no encontrada con ID: " + id);
        }
        roomRepository.deleteById(id);
    }
}