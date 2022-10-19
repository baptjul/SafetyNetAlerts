package com.safetynet.safetynetalerts.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Firestations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getFireStationTest() throws Exception {
        mockMvc.perform(get("/firestation")).andExpect(status().isOk());
    }

    @Test
    public void addFireStationTest() throws Exception {
        mockMvc.perform(post("/firestation/addfirestation").content(asJsonString(new Firestations("aaaa", "1"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void deleteFireStationTest() throws Exception {
        mockMvc.perform(delete("/firestation")).andExpect(status().isOk());
    }

    @Test
    public void updateFireStationTest() throws Exception {
        mockMvc.perform(put("/firestation/aaaa").content(asJsonString(new Firestations("aaaa", "1"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getPersonByStationTest() throws Exception {
        mockMvc.perform(get("/flood?station=1")).andExpect(status().isOk());
    }

    @Test
    public void getPhoneListByStationTest() throws Exception {
        mockMvc.perform(get("/phoneAlert?station=1")).andExpect(status().isOk());
    }

    @Test
    public void getPersonListByFireStationTest() throws Exception {
        mockMvc.perform(get("/fire?address=aaaa")).andExpect(status().isOk());
    }

    @Test
    public void getPersonListByStationTest() throws Exception {
        mockMvc.perform(get("/fireStation?station=1")).andExpect(status().isOk());
    }

    @Test
    public void findFireStationTest() throws Exception {
        mockMvc.perform(get("/firestation/1509 Culver St")).andExpect(status().isOk());
    }
}
