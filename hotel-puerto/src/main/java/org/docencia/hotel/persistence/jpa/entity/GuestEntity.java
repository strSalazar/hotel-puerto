package org.docencia.hotel.persistence.jpa.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "guest")
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;
    private String email;
    private String phone;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GuestEntity)) {
            return false;
        }
        GuestEntity guestEntity = (GuestEntity) o;
        return Objects.equals(id, guestEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}