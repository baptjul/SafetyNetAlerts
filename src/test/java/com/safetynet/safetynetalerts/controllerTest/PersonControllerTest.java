package com.safetynet.safetynetalerts.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.model.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

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
    public void getPersonsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person")).andExpect(status().isOk());
    }

    @Test
    public void getPersonByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person?name=john")).andExpect(status().isOk());
    }

    @Test
    public void addPersonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/person/addperson").content(asJsonString(new Persons("jane", "doe", "rue de la republique", "stras", "12345", "12345678910", "janedoe@gmail.com"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void updatePersonTest() throws Exception {
        mockMvc.perform(put("/person/John/Boyd").content(asJsonString(new Persons("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/Roger/Boyd")).andExpect(status().isOk());
    }

    @Test
    public void personsMailTest() throws Exception {

        mockMvc.perform(get("/communityEmail?city=stras")).andExpect(status().isOk());
    }

    @Test
    public void personListMedicationTest() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=john&lastName=doe")).andExpect(status().isOk());
    }

    @Test
    public void getChildrenByAddress() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/childAlert?address=AAAA")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(status, 200);
    }
}
