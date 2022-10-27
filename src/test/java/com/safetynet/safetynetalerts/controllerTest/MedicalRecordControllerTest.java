package com.safetynet.safetynetalerts.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

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
    public void getMedicalRecordTest() throws Exception {
        mockMvc.perform(get("/medicalrecord")).andExpect(status().isOk());
    }

    @Test
    public void addMedicalRecordTest() throws Exception {
        mockMvc.perform(post("/medicalrecord/addmedicalrecord").content(asJsonString(new Medicalrecords("john", "doe", "01/02/1999", List.of(""), List.of("")))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecordTest() throws Exception {
        mockMvc.perform(put("/medicalrecord/Roger/Boyd").content(asJsonString(new Medicalrecords("Roger", "Boyd", "09/06/2017", List.of(""), List.of("")))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(delete("/medicalrecord/Roger/Boyd")).andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecordByNameTest() throws Exception {
        mockMvc.perform(get("/medicalrecord/John/Boyd")).andExpect(status().isOk());
    }
}
