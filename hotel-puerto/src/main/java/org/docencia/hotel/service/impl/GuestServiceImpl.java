package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.nosql.GuestPreferencesRepository;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestJpaRepository guestJpaRepository;
    private final GuestPreferencesRepository guestPreferencesRepository;
    private final GuestMapper guestMapper;
    private final GuestPreferencesMapper guestPreferencesMapper;

    public GuestServiceImpl(GuestJpaRepository guestJpaRepository,
            GuestPreferencesRepository guestPreferencesRepository,
            GuestMapper guestMapper,
            GuestPreferencesMapper guestPreferencesMapper) {
        this.guestJpaRepository = guestJpaRepository;
        this.guestPreferencesRepository = guestPreferencesRepository;
        this.guestMapper = guestMapper;
        this.guestPreferencesMapper = guestPreferencesMapper;
    }

    @Override
    @Transactional
    public Guest createGuest(Guest guest) {
        GuestEntity entity = guestMapper.toEntity(guest);
        entity = guestJpaRepository.save(entity);

        if (guest.getPreferences() != null) {
            GuestPreferencesDocument doc = guestPreferencesMapper.toDocument(guest.getPreferences());
            doc.setGuestId(entity.getId());
            guestPreferencesRepository.save(doc);
        }

        return getGuestById(entity.getId());
    }

    @Override
    public Guest getGuestById(Long id) {
        GuestEntity entity = guestJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found: " + id));
        Guest guest = guestMapper.toDomain(entity);

        guestPreferencesRepository.findByGuestId(id).ifPresent(doc -> {
            GuestPreferences prefs = guestPreferencesMapper.toDomain(doc);
            guest.setPreferences(prefs);
        });

        return guest;
    }
}