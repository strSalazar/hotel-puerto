package org.docencia.hotel.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GuestController.class)
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestDomain guestDomain;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createGuest_ShouldReturnCreatedGuest() throws Exception {
        Guest guest = new Guest();
        guest.setFullName("Jane Doe");
        guest.setEmail("jane@example.com");

        Guest savedGuest = new Guest();
        savedGuest.setId(1L);
        savedGuest.setFullName("Jane Doe");
        savedGuest.setEmail("jane@example.com");

        when(guestDomain.registerGuest(any(Guest.class))).thenReturn(savedGuest);

        mockMvc.perform(post("/api/guests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(guest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Jane Doe"));
    }
}