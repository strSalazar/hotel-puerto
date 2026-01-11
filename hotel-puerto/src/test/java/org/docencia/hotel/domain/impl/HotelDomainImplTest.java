package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.service.api.HotelService;
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
class HotelDomainImplTest {

    @Mock
    private HotelService service;

    @InjectMocks
    private HotelDomainImpl domain;

    @Test
    void createHotelTest() {
        Hotel hotel = new Hotel();
        when(service.createHotel(hotel)).thenReturn(hotel);

        Hotel result = domain.createHotel(hotel);

        assertEquals(hotel, result);
        verify(service).createHotel(hotel);
    }

    @Test
    void getHotelTest() {
        Long id = 1L;
        Hotel hotel = new Hotel();
        when(service.getHotelById(id)).thenReturn(hotel);

        Hotel result = domain.getHotel(id);

        assertEquals(hotel, result);
        verify(service).getHotelById(id);
    }

    @Test
    void getAllHotelsTest() {
        List<Hotel> list = Collections.emptyList();
        when(service.getAllHotels()).thenReturn(list);

        List<Hotel> result = domain.getAllHotels();

        assertEquals(list, result);
        verify(service).getAllHotels();
    }

    @Test
    void updateHotelTest() {
        Long id = 1L;
        Hotel hotel = new Hotel();
        when(service.updateHotel(id, hotel)).thenReturn(hotel);

        Hotel result = domain.updateHotel(id, hotel);

        assertEquals(hotel, result);
        verify(service).updateHotel(id, hotel);
    }

    @Test
    void deleteHotelTest() {
        Long id = 1L;

        domain.deleteHotel(id);

        verify(service).deleteHotel(id);
    }
}