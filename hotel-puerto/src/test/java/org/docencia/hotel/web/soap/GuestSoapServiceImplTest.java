package org.docencia.hotel.web.soap;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestSoapServiceImplTest {

    @Mock
    private GuestDomain guestDomain;

    @InjectMocks
    private GuestSoapServiceImpl soapService;

    @Test
    void getGuestById_ShouldDelegateToDomain() {
        Long id = 1L;
        Guest guest = new Guest();
        when(guestDomain.getGuest(id)).thenReturn(guest);
        
        Guest result = soapService.getGuestById(id);
        
        assertEquals(guest, result);
        verify(guestDomain).getGuest(id);
    }

    @Test
    void createGuest_ShouldDelegateToDomain() {
        Guest guest = new Guest();
        when(guestDomain.registerGuest(guest)).thenReturn(guest);
        
        Guest result = soapService.saveGuest(guest); // Asegúrate que el método se llame 'createGuest' en tu impl SOAP
        
        assertEquals(guest, result);
        verify(guestDomain).registerGuest(guest);
    }
}