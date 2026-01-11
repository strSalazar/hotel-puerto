package org.docencia.hotel.service.api;

import java.util.List;

import org.docencia.hotel.domain.model.Room;

public interface RoomService {
    
    /**
     * crea una nueva habitacion en el sistema
     * 
     * @param room la habitacion a crear
     * @return Room
     */
    Room createRoom(Room room);
    
    /**
     * actualiza una habitacion existente
     * 
     * @param id el id de la habitacion a actualizar
     * @param room los nuevos datos de la habitacion
     * @return Room
     */
    Room updateRoom(Long id, Room room);
    
    /**
     * obtiene una habitacion por su id
     * 
     * @param id el id de la habitacion
     * @return Room
     */
    Room getRoomById(Long id);
    
    /**
     * obtiene todas las habitaciones del sistema
     * 
     * @return List<Room>
     */
    List<Room> getAllRooms();
    
    /**
     * elimina una habitacion del sistema
     * 
     * @param id el id de la habitacion a eliminar
     */
    void deleteRoom(Long id);
}