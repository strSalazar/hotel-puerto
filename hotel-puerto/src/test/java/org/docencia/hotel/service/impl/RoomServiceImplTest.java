package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository repository;
    @Mock
    private RoomMapper mapper;

    @InjectMocks
    private RoomServiceImpl service;

    @Test
    void createRoomTest() {
        Room room = new Room();
        room.setId(1L);
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);

        when(mapper.toEntity(room)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(room);

        Room result = service.createRoom(room);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void getRoomByIdTest() {
        Long id = 1L;
        RoomEntity entity = new RoomEntity();
        entity.setId(id);
        Room room = new Room();
        room.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(room);

        Room result = service.getRoomById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}