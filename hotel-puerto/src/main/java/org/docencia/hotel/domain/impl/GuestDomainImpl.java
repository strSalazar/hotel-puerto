package org.docencia.hotel.domain.impl;

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
}