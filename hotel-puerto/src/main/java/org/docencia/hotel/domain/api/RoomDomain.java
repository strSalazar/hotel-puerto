package org.docencia.hotel.domain.api;

import java.util.List;

import org.docencia.hotel.domain.model.Room;

public interface RoomDomain {
    
    /**
     * obtiene una habitacion por su id
     * 
     * @param id el id de la habitacion
     * @return Room
     */
    Room getRoom(Long id);

    /**
     * obtiene todas las habitaciones disponibles
     * 
     * @return List<Room>
     */
    List<Room> getAllRooms();

    /**
     * crea una nueva habitacion
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
     * elimina una habitacion del sistema
     * 
     * @param id el id de la habitacion a eliminar
     */
    void deleteRoom(Long id);
}