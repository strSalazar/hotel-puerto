package org.docencia.hotel.mapper;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.BookingMapper;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MapperTests {

    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private GuestPreferencesMapper guestPreferencesMapper;

    @Test
    void testBookingMapper() {
        Booking booking = new Booking();
        booking.setId(1L);
        BookingEntity entity = bookingMapper.toEntity(booking);
        assertNotNull(entity);
    }

    @Test
    void testGuestMapper() {
        Guest guest = new Guest();
        guest.setId(1L);
        guest.setFullName("John Doe");
        
        GuestEntity entity = guestMapper.toEntity(guest);
        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("John Doe", entity.getFullName());

        Guest result = guestMapper.toDomain(entity);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getFullName());
    }

    @Test
    void testHotelMapper() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Grand Hotel");
        
        HotelEntity entity = hotelMapper.toEntity(hotel);
        assertNotNull(entity);
        assertEquals(1L, entity.getId());

        Hotel result = hotelMapper.toDomain(entity);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testRoomMapper() {
        Room room = new Room();
        room.setId(1L);
        RoomEntity entity = roomMapper.toEntity(room);
        assertNotNull(entity);
        assertEquals(1L, entity.getId());

        Room result = roomMapper.toDomain(entity);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGuestPreferencesMapper() {
        GuestPreferences prefs = new GuestPreferences();
        prefs.setVip(true); 
        
        GuestPreferencesDocument doc = guestPreferencesMapper.toDocument(prefs);
        assertNotNull(doc);
        assertTrue(doc.isVip());

        GuestPreferences result = guestPreferencesMapper.toDomain(doc);
        assertNotNull(result);
        assertTrue(result.isVip());
    }
}