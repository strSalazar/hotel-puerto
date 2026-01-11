package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.RoomService;
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
class RoomDomainImplTest {

    @Mock
    private RoomService service;

    @InjectMocks
    private RoomDomainImpl domain;

    @Test
    void createRoomTest() {
        Room room = new Room();
        when(service.createRoom(room)).thenReturn(room);

        Room result = domain.createRoom(room);

        assertEquals(room, result);
        verify(service).createRoom(room);
    }

    @Test
    void getRoomTest() {
        Long id = 1L;
        Room room = new Room();
        when(service.getRoomById(id)).thenReturn(room);

        Room result = domain.getRoom(id);

        assertEquals(room, result);
        verify(service).getRoomById(id);
    }

    @Test
    void getAllRoomsTest() {
        List<Room> list = Collections.emptyList();
        when(service.getAllRooms()).thenReturn(list);

        List<Room> result = domain.getAllRooms();

        assertEquals(list, result);
        verify(service).getAllRooms();
    }

    @Test
    void updateRoomTest() {
        Long id = 1L;
        Room room = new Room();
        when(service.updateRoom(id, room)).thenReturn(room);

        Room result = domain.updateRoom(id, room);

        assertEquals(room, result);
        verify(service).updateRoom(id, room);
    }

    @Test
    void deleteRoomTest() {
        Long id = 1L;

        domain.deleteRoom(id);

        verify(service).deleteRoom(id);
    }
}