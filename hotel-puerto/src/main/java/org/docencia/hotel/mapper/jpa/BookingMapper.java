package org.docencia.hotel.mapper.jpa;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "guest", ignore = true)
    BookingEntity toEntity(Booking domain);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "guest.id", target = "guestId")
    Booking toDomain(BookingEntity entity);
}