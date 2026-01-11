package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Guest;
import java.util.List;

public interface GuestService {
    Guest createGuest(Guest guest);
    Guest updateGuest(Long id, Guest guest);
    Guest getGuestById(Long id);
    List<Guest> getAllGuests();
    void deleteGuest(Long id);
}