package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * crea un nuevo huesped en el sistema (polyglot: jpa + mongodb)
     * 
     * @param guest el huesped a crear incluyendo preferencias
     * @return Guest
     */
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

    /**
     * actualiza un huesped existente (datos en jpa y preferencias en mongodb)
     * 
     * @param id el id del huesped a actualizar
     * @param guest los nuevos datos del huesped
     * @return Guest
     */
    @Override
    @Transactional
    public Guest updateGuest(Long id, Guest guest) {
        GuestEntity entity = guestJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("huesped no encontrado con id: " + id));

        entity.setFullName(guest.getFullName());
        entity.setEmail(guest.getEmail());
        entity.setPhone(guest.getPhone());
        guestJpaRepository.save(entity);

        if (guest.getPreferences() != null) {
            GuestPreferencesDocument doc = guestPreferencesRepository.findByGuestId(id)
                    .orElse(new GuestPreferencesDocument());

            doc.setGuestId(id);
            doc.setRoomTypePreference(guest.getPreferences().getRoomTypePreference());
            doc.setDietaryRequirements(guest.getPreferences().getDietaryRequirements());
            doc.setVip(guest.getPreferences().isVip());

            guestPreferencesRepository.save(doc);
        }

        return getGuestById(id);
    }

    /**
     * obtiene un huesped por su id incluyendo sus preferencias
     * 
     * @param id el id del huesped
     * @return Guest
     */
    @Override
    public Guest getGuestById(Long id) {
        GuestEntity entity = guestJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("huesped no encontrado con id: " + id));
        Guest guest = guestMapper.toDomain(entity);

        guestPreferencesRepository.findByGuestId(id).ifPresent(doc -> {
            GuestPreferences prefs = guestPreferencesMapper.toDomain(doc);
            guest.setPreferences(prefs);
        });

        return guest;
    }

    /**
     * obtiene todos los huéspedes del sistema con sus preferencias
     * 
     * @return List
     */
    @Override
    public List<Guest> getAllGuests() {
        return guestJpaRepository.findAll().stream()
                .map(entity -> {
                    Guest guest = guestMapper.toDomain(entity);
                    guestPreferencesRepository.findByGuestId(entity.getId())
                            .ifPresent(doc -> guest.setPreferences(guestPreferencesMapper.toDomain(doc)));
                    return guest;
                })
                .collect(Collectors.toList());
    }

    /**
     * elimina un huesped del sistema (datos en jpa y preferencias en mongodb)
     * 
     * @param id el id del huesped a eliminar
     */
    @Override
    @Transactional
    public void deleteGuest(Long id) {
        if (!guestJpaRepository.existsById(id)) {
            throw new RuntimeException("huesped no encontrado con id: " + id);
        }
        guestPreferencesRepository.deleteByGuestId(id);
        guestJpaRepository.deleteById(id);
    }
}