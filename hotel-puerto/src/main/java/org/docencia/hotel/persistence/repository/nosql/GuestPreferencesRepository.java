package org.docencia.hotel.persistence.repository.nosql;

import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestPreferencesRepository extends MongoRepository<GuestPreferencesDocument, String> {
    Optional<GuestPreferencesDocument> findByGuestId(Long guestId);
    void deleteByGuestId(Long guestId);
}