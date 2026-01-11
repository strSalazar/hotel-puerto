package org.docencia.hotel.persistence.repository.jpa;

import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
