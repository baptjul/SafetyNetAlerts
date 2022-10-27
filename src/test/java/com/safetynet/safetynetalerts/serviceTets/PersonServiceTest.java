package com.safetynet.safetynetalerts.serviceTets;

import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.model.Persons;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import com.safetynet.safetynetalerts.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonServiceTest {

    private static PersonService personService;
    @Mock
    private static PersonRepository personRepository;
    @Mock
    private static MedicalRecordRepository medicalRecordRepository;
    private ArrayList<Persons> personList;
    private ArrayList<Medicalrecords> medicalList;

    @BeforeEach
    void setup() {
        personService = new PersonService(personRepository, medicalRecordRepository);
        personList = new ArrayList<>();
        Persons person = new Persons("john", "doe", "42 rue des ponts", "metz", "12345", "12345678910", "johndoe@gmail.com");
        personList.add(person);
        medicalList = new ArrayList<>();
        Medicalrecords medicalRecord = new Medicalrecords("john", "doe", "01/02/1999", List.of(""), List.of(""));
        medicalList.add(medicalRecord);

    }

    @AfterEach
    void clearList() {
        personList.clear();
        medicalList.clear();
    }

    @Test
    public void getPersonTest() {

        when(personRepository.getPersonList()).thenReturn(personList);

        assertEquals(1, personService.getPersons().size());

        verify(personRepository, times(1)).getPersonList();
    }

    @Test
    public void addPersonToListTest() {
        Persons person = new Persons("jane", "doe", "rue de la republique", "stras", "12345", "12345678910", "janedoe@gmail.com");
        when(personRepository.addPerson(person)).thenReturn(personList);
        personService.addPerson(person);
        verify(personRepository, times(1)).addPerson(person);

    }

    @Test
    public void deletePersonToListTest() {
        Persons person = new Persons("jane", "doe", "rue de la republique", "stras", "12345", "12345678910", "janedoe@gmail.com");
        when(personRepository.addPerson(person)).thenReturn(personList);
        personService.addPerson(person);
        verify(personRepository, times(1)).addPerson(person);
        when(personRepository.deletePerson("jane", "doe")).thenReturn(personList);
        personService.deletePerson("jane", "doe");
        verify(personRepository, times(1)).deletePerson("jane", "doe");

    }

    @Test
    public void findPersonTest() {
        Persons person = new Persons("jane", "doe", "rue de la republique", "stras", "12345", "12345678910", "janedoe@gmail.com");
        when(personRepository.findPerson("jane", "doe")).thenReturn(person);
        personService.findPerson("jane", "doe");
        verify(personRepository, times(1)).findPerson("jane", "doe");
    }

    @Test
    public void updatePersonTest() {
        Persons person = new Persons("john", "doe", "42 rue des ponts", "stras", "12345", "12345678910", "johndoe@gmail.com");
        when(personRepository.updatePerson(person, "john", "doe")).thenReturn(personList);
        personService.updatePerson(person, "john", "doe");
        System.out.println(person);
        verify(personRepository, times(1)).updatePerson(person, "john", "doe");
    }

    @Test
    public void getMailsByCityTest() {
        when(personRepository.getPersonList()).thenReturn(personList);

        assertEquals("[johndoe : johndoe@gmail.com]", personService.getMailPersons("metz").toString());
    }

    @Test
    public void getPersonListMedicationTest() {
        when(personRepository.getPersonList()).thenReturn(personList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalList);

        assertEquals(1, personService.personListMedication("john", "doe").size());
    }

    @Test
    public void getAdultListTest() {
        when(personRepository.getPersonList()).thenReturn(personList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalList);

        assertEquals(1, personService.adultList("42 rue des ponts").size());
    }

    @Test
    public void getChildrenListTest() {
        when(personRepository.getPersonList()).thenReturn(personList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalList);

        assertEquals(0, personService.childrenList("42 rue des ponts").size());
    }
}
