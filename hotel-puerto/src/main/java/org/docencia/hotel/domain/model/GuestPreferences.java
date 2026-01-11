package org.docencia.hotel.domain.model;

import java.util.Objects;

public class GuestPreferences {
    private String id;
    private Long guestId;
    private String roomTypePreference;
    private String dietaryRequirements;
    private boolean vip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getRoomTypePreference() {
        return roomTypePreference;
    }

    public void setRoomTypePreference(String roomTypePreference) {
        this.roomTypePreference = roomTypePreference;
    }

    public String getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(String dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GuestPreferences)) {
            return false;
        }
        GuestPreferences guestPreferences = (GuestPreferences) o;
        return Objects.equals(id, guestPreferences.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}