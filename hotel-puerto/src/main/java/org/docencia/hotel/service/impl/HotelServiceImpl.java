package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.service.api.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    /**
     * crea un nuevo hotel en el sistema
     *
     * @param hotel el hotel a crear
     * @return Hotel
     */
    @Override
    @Transactional
    public Hotel createHotel(Hotel hotel) {
        HotelEntity entity = hotelMapper.toEntity(hotel);
        entity = hotelRepository.save(entity);
        return hotelMapper.toDomain(entity);
    }

    /**
     * actualiza un hotel existente
     *
     * @param id el id del hotel a actualizar
     * @param hotel los nuevos datos del hotel
     * @return Hotel
     */
    @Override
    @Transactional
    public Hotel updateHotel(Long id, Hotel hotel) {
        return hotelRepository.findById(id)
                .map(entity -> {
                    entity.setName(hotel.getName());
                    entity.setAddress(hotel.getAddress());
                    return hotelMapper.toDomain(hotelRepository.save(entity));
                })
                .orElseThrow(() -> new RuntimeException("hotel no encontrado con id: " + id));
    }

    /**
     * obtiene un hotel por su id
     *
     * @param id el id del hotel
     * @return Hotel
     */
    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("hotel no encontrado con id: " + id));
    }

    /**
     * obtiene todos los hoteles del sistema
     *
     * @return List
     */
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * elimina un hotel del sistema
     *
     * @param id el id del hotel a eliminar
     */
    @Override
    @Transactional
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("hotel no encontrado con id: " + id);
        }
        hotelRepository.deleteById(id);
    }
}