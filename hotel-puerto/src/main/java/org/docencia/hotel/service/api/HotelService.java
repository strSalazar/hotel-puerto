package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Hotel;
import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);
    Hotel updateHotel(Long id, Hotel hotel);
    Hotel getHotelById(Long id);
    List<Hotel> getAllHotels();
    void deleteHotel(Long id);
}