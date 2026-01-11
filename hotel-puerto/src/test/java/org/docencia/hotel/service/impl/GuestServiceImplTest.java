package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.nosql.GuestPreferencesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    private GuestJpaRepository jpaRepository;
    @Mock
    private GuestPreferencesRepository mongoRepository;
    @Mock
    private GuestMapper guestMapper;
    @Mock
    private GuestPreferencesMapper preferencesMapper;

    @InjectMocks
    private GuestServiceImpl guestService;

    @Test
    void createGuest_ShouldSaveAndReturnGuest() {
        Guest guest = new Guest();
        guest.setFullName("John Doe");
        
        GuestPreferences prefs = new GuestPreferences();
        prefs.setRoomTypePreference("Suite");
        guest.setPreferences(prefs);

        GuestEntity entity = new GuestEntity();
        entity.setId(1L);
        entity.setFullName("John Doe");

        GuestPreferencesDocument doc = new GuestPreferencesDocument();
        doc.setRoomTypePreference("Suite");

        when(guestMapper.toEntity(guest)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(entity);
        when(preferencesMapper.toDocument(prefs)).thenReturn(doc);
        when(mongoRepository.save(doc)).thenReturn(doc);
        
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(guestMapper.toDomain(entity)).thenReturn(guest);
        when(mongoRepository.findByGuestId(1L)).thenReturn(Optional.of(doc));
        when(preferencesMapper.toDomain(doc)).thenReturn(prefs);

        Guest result = guestService.createGuest(guest);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(jpaRepository).save(entity);
        verify(mongoRepository).save(doc);
    }

    @Test
    void getGuestById_ShouldReturnGuest_WhenFound() {
        Long id = 1L;
        GuestEntity entity = new GuestEntity();
        entity.setId(id);
        Guest guest = new Guest();
        guest.setId(id);

        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(guestMapper.toDomain(entity)).thenReturn(guest);
        when(mongoRepository.findByGuestId(id)).thenReturn(Optional.empty());

        Guest result = guestService.getGuestById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}