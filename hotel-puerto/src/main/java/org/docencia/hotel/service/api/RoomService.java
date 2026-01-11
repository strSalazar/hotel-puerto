package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Room;
import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Long id, Room room);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    void deleteRoom(Long id);
}