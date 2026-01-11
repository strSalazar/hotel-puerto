package org.docencia.hotel.service.api;

import java.util.List;

import org.docencia.hotel.domain.model.Booking;

public interface BookingService {
    
    /**
     * crea una nueva reserva en el sistema
     * 
     * @param booking la reserva a crear
     * @return Booking
     */
    Booking createBooking(Booking booking);
    
    /**
     * actualiza una reserva existente
     * 
     * @param id el id de la reserva a actualizar
     * @param booking los nuevos datos de la reserva
     * @return Booking
     */
    Booking updateBooking(Long id, Booking booking);
    
    /**
     * obtiene una reserva por su id
     * 
     * @param id el id de la reserva
     * @return Booking
     */
    Booking getBookingById(Long id);
    
    /**
     * obtiene todas las reservas del sistema
     * 
     * @return List<Booking>
     */
    List<Booking> getAllBookings();
    
    /**
     * elimina una reserva del sistema
     * 
     * @param id el id de la reserva a eliminar
     */
    void deleteBooking(Long id);
}