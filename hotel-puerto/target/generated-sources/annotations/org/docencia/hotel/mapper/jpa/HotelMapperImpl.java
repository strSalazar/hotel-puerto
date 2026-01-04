package org.docencia.hotel.mapper.jpa;

import javax.annotation.processing.Generated;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-04T14:09:26+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class HotelMapperImpl implements HotelMapper {

    @Override
    public HotelEntity toEntity(Hotel domain) {
        if ( domain == null ) {
            return null;
        }

        HotelEntity hotelEntity = new HotelEntity();

        return hotelEntity;
    }

    @Override
    public Hotel toDomain(HotelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        return hotel;
    }
}
