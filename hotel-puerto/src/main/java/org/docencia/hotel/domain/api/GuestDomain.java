package org.docencia.hotel.domain.api;

import java.util.List;

import org.docencia.hotel.domain.model.Guest;

public interface GuestDomain {
    
    /**
     * obtiene un huesped por su id
     * 
     * @param id el id del huesped
     * @return Guest
     */
    Guest getGuest(Long id);

    /**
     * registra un nuevo huesped en el sistema
     * 
     * @param guest el huesped a registrar
     * @return Guest
     */
    Guest registerGuest(Guest guest);

    /**
     * obtiene todos los huespedes del sistema
     * 
     * @return List<Guest>
     */
    List<Guest> getAllGuests();

    /**
     * actualiza un huesped existente
     * 
     * @param id el id del huesped a actualizar
     * @param guest los nuevos datos del huesped
     * @return Guest
     */
    Guest updateGuest(Long id, Guest guest);

    /**
     * elimina un huesped del sistema
     * 
     * @param id el id del huesped a eliminar
     */
    void deleteGuest(Long id);
}