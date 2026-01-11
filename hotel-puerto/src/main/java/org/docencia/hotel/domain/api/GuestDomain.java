package org.docencia.hotel.domain.api;

import org.docencia.hotel.domain.model.Guest;

public interface GuestDomain {
    Guest getGuest(Long id);
    Guest registerGuest(Guest guest);
}