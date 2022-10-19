package com.safetynet.safetynetalerts.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(MockMvcRequestBuilders.put("/person/jane/doe").content(asJsonString(new Persons("jane", "doe", "42 rue des ponts", "stras", "12345", "12345678910", "janedoe@gmail.com"))).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void deletePersonTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/person")).andExpect(status().isOk());
    }

    @Test
    public void personsMailTest() throws Exception {

        mockMvc.perform(get("/communityEmail?city=stras")).andExpect(status().isOk());
    }

    @Test
    public void personListMedicationTest() throws Exception {

        mockMvc.perform(get("/personInfo?firstname=tonton&lastname=tata")).andExpect(status().isOk());
    }

    @Test
    public void getChildrenByAddress() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/childAlert?address=AAAA")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(status, 200);
    }
}
