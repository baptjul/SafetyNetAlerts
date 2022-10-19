package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component("DataSource")
public class DataSource {
    private  List<Persons> persons = new ArrayList<>();
    private  List<Firestations> firestations = new ArrayList<>();
    private  List<Medicalrecords> medicalrecords = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/json/data.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        DataSource dataSource = objectMapper.readValue(jsonData, DataSource.class);

        this.persons = dataSource.getPersons();
        this.firestations = dataSource.getFirestations();
        this.medicalrecords = dataSource.getMedicalrecords();
    }

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }

    public List<Firestations> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestations> firestations) {
        this.firestations = firestations;
    }

    public List<Medicalrecords> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<Medicalrecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
