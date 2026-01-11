package org.docencia.hotel.domain.api;

import java.util.List;

import org.docencia.hotel.domain.model.Hotel;

public interface HotelDomain {
    
    /**
     * obtiene un hotel por su id
     * 
     * @param id el id del hotel
     * @return Hotel
     */
    Hotel getHotel(Long id);

    /**
     * obtiene todos los hoteles disponibles
     * 
     * @return List<Hotel>
     */
    List<Hotel> getAllHotels();

    /**
     * crea un nuevo hotel
     * 
     * @param hotel el hotel a crear
     * @return Hotel
     */
    Hotel createHotel(Hotel hotel);

    /**
     * actualiza un hotel existente
     * 
     * @param id el id del hotel a actualizar
     * @param hotel los nuevos datos del hotel
     * @return Hotel
     */
    Hotel updateHotel(Long id, Hotel hotel);

    /**
     * elimina un hotel del sistema
     * 
     * @param id el id del hotel a eliminar
     */
    void deleteHotel(Long id);
}