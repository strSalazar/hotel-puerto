package org.docencia.hotel.service.api;

import java.util.List;

import org.docencia.hotel.domain.model.Hotel;

public interface HotelService {
    
    /**
     * crea un nuevo hotel en el sistema
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
     * obtiene un hotel por su id
     * 
     * @param id el id del hotel
     * @return Hotel
     */
    Hotel getHotelById(Long id);
    
    /**
     * obtiene todos los hoteles del sistema
     * 
     * @return List<Hotel>
     */
    List<Hotel> getAllHotels();
    
    /**
     * elimina un hotel del sistema
     * 
     * @param id el id del hotel a eliminar
     */
    void deleteHotel(Long id);
}