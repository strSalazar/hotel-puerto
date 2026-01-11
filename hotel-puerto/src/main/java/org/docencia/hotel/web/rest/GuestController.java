package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Guests", description = "operaciones rest de huespedes")
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestDomain guestDomain;

    public GuestController(GuestDomain guestDomain) {
        this.guestDomain = guestDomain;
    }

    /**
     * obtiene un huesped por su id
     * 
     * @param id el id del huesped
     * @return ResponseEntity
     */
    @Operation(summary = "obtener huesped por id")
    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable Long id) {
        try {
            Guest guest = guestDomain.getGuest(id);
            return ResponseEntity.ok(guest);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * guarda un nuevo huesped en el sistema
     * 
     * @param guest el huesped a guardar
     * @return ResponseEntity
     */
    @Operation(summary = "guardar huesped")
    @PostMapping
    public ResponseEntity<Guest> save(@Valid @RequestBody Guest guest) {
        Guest saved = guestDomain.registerGuest(guest);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}