package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.service.api.GuestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestDomainImplTest {

    @Mock
    private GuestService service;

    @InjectMocks
    private GuestDomainImpl domain;

    @Test
    void registerGuestTest() {
        Guest guest = new Guest();
        when(service.createGuest(guest)).thenReturn(guest);

        Guest result = domain.registerGuest(guest); // Método específico: registerGuest

        assertEquals(guest, result);
        verify(service).createGuest(guest);
    }

    @Test
    void getGuestTest() {
        Long id = 1L;
        Guest guest = new Guest();
        when(service.getGuestById(id)).thenReturn(guest);

        Guest result = domain.getGuest(id);

        assertEquals(guest, result);
        verify(service).getGuestById(id);
    }

    @Test
    void getAllGuestsTest() {
        List<Guest> list = Collections.emptyList();
        when(service.getAllGuests()).thenReturn(list);

        List<Guest> result = domain.getAllGuests();

        assertEquals(list, result);
        verify(service).getAllGuests();
    }

    @Test
    void updateGuestTest() {
        Long id = 1L;
        Guest guest = new Guest();
        when(service.updateGuest(id, guest)).thenReturn(guest);

        Guest result = domain.updateGuest(id, guest);

        assertEquals(guest, result);
        verify(service).updateGuest(id, guest);
    }

    @Test
    void deleteGuestTest() {
        Long id = 1L;

        domain.deleteGuest(id);

        verify(service).deleteGuest(id);
    }
}