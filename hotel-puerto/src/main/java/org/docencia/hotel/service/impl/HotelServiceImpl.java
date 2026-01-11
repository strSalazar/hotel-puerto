package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.service.api.HotelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelServiceImpl(HotelRepository hotelRepository, HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    @Override
    @Transactional
    public Hotel createHotel(Hotel hotel) {
        HotelEntity entity = hotelMapper.toEntity(hotel);
        entity = hotelRepository.save(entity);
        return hotelMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Hotel updateHotel(Long id, Hotel hotel) {
        return hotelRepository.findById(id)
                .map(entity -> {
                    entity.setName(hotel.getName());
                    entity.setAddress(hotel.getAddress());
                    return hotelMapper.toDomain(hotelRepository.save(entity));
                })
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado con ID: " + id));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Hotel no encontrado con ID: " + id);
        }
        hotelRepository.deleteById(id);
    }
}