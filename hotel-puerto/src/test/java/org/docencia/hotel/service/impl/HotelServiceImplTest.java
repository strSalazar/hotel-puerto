package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @Mock
    private HotelRepository repository;
    @Mock
    private HotelMapper mapper;

    @InjectMocks
    private HotelServiceImpl service;

    @Test
    void createHotelSaveTest() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        HotelEntity entity = new HotelEntity();
        entity.setId(1L);

        when(mapper.toEntity(hotel)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(hotel);

        Hotel result = service.createHotel(hotel);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void getHotelByIdTest() {
        Long id = 1L;
        HotelEntity entity = new HotelEntity();
        entity.setId(id);
        Hotel hotel = new Hotel();
        hotel.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(hotel);

        Hotel result = service.getHotelById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}