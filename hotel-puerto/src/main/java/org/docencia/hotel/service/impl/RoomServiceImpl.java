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

    /**
     * crea una nueva habitacion en el sistema asociada a un hotel
     * 
     * @param room la habitacion a crear
     * @return Room
     */
    @Override
    @Transactional
    public Room createRoom(Room room) {
        HotelEntity hotel = hotelRepository.findById(room.getHotelId())
                .orElseThrow(() -> new RuntimeException("hotel no encontrado con id: " + room.getHotelId()));

        RoomEntity entity = roomMapper.toEntity(room);
        entity.setHotel(hotel);

        entity = roomRepository.save(entity);
        return roomMapper.toDomain(entity);
    }

    /**
     * actualiza una habitacion existente incluyendo su asignacion de hotel
     * 
     * @param id el id de la habitacion a actualizar
     * @param room los nuevos datos de la habitacion
     * @return Room
     */
    @Override
    @Transactional
    public Room updateRoom(Long id, Room room) {
        RoomEntity entity = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("habitacion no encontrada con id: " + id));

        entity.setNumber(room.getNumber());
        entity.setType(room.getType());
        entity.setPricePerNight(room.getPricePerNight());

        if (!entity.getHotel().getId().equals(room.getHotelId())) {
            HotelEntity newHotel = hotelRepository.findById(room.getHotelId())
                    .orElseThrow(() -> new RuntimeException("hotel no encontrado con id: " + room.getHotelId()));
            entity.setHotel(newHotel);
        }

        return roomMapper.toDomain(roomRepository.save(entity));
    }

    /**
     * obtiene una habitacion por su id
     * 
     * @param id el id de la habitacion
     * @return Room
     */
    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("habitacion no encontrada con id: " + id));
    }

    /**
     * obtiene todas las habitaciones del sistema
     * 
     * @return List
     */
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * elimina una habitacion del sistema
     * 
     * @param id el id de la habitacion a eliminar
     */
    @Override
    @Transactional
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("habitacion no encontrada con id: " + id);
        }
        roomRepository.deleteById(id);
    }
}