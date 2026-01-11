package org.docencia.hotel.service.api;

import java.util.List;

import org.docencia.hotel.domain.model.Guest;

public interface GuestService {
    
    /**
     * crea un nuevo huesped en el sistema
     * 
     * @param guest el huesped a crear
     * @return Guest
     */
    Guest createGuest(Guest guest);
    
    /**
     * actualiza un huesped existente
     * 
     * @param id el id del huesped a actualizar
     * @param guest los nuevos datos del huesped
     * @return Guest
     */
    Guest updateGuest(Long id, Guest guest);
    
    /**
     * obtiene un huesped por su id
     * 
     * @param id el id del huesped
     * @return Guest
     */
    Guest getGuestById(Long id);
    
    /**
     * obtiene todos los huéspedes del sistema
     * 
     * @return List<Guest>
     */
    List<Guest> getAllGuests();
    
    /**
     * elimina un huesped del sistema
     * 
     * @param id el id del huesped a eliminar
     */
    void deleteGuest(Long id);
}