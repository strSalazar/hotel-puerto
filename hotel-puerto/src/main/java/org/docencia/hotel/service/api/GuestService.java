package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Guest;

public interface GuestService {
    Guest createGuest(Guest guest);
    Guest getGuestById(Long id);
    // otros métodos como update, delete...
}