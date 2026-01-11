package org.docencia.hotel.domain.impl;

import java.util.List;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomDomainImpl implements RoomDomain {

    private final RoomService service;

    public RoomDomainImpl(RoomService service) {
        this.service = service;
    }

    @Override
    public Room getRoom(Long id) {
        return service.getRoomById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return service.getAllRooms();
    }

    @Override
    public Room createRoom(Room room) {
        return service.createRoom(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        return service.updateRoom(id, room);
    }

    @Override
    public void deleteRoom(Long id) {
        service.deleteRoom(id);
    }
}