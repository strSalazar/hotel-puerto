package org.docencia.hotel.persistence.jpa.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String type;
    private Double pricePerNight;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RoomEntity)) {
            return false;
        }
        RoomEntity roomEntity = (RoomEntity) o;
        return Objects.equals(id, roomEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}