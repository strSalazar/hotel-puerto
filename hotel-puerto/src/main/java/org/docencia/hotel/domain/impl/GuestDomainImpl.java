package org.docencia.hotel.domain.impl;

import java.util.List;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.stereotype.Service;

@Service
public class GuestDomainImpl implements GuestDomain {

    private final GuestService service;

    public GuestDomainImpl(GuestService service) {
        this.service = service;
    }

    @Override
    public Guest getGuest(Long id) {
        return service.getGuestById(id);
    }

    @Override
    public Guest registerGuest(Guest guest) {
        return service.createGuest(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        return service.getAllGuests();
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        return service.updateGuest(id, guest);
    }

    @Override
    public void deleteGuest(Long id) {
        service.deleteGuest(id);
    }
}