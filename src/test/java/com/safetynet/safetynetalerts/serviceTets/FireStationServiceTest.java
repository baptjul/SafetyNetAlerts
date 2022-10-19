package com.safetynet.safetynetalerts.serviceTets;

import com.safetynet.safetynetalerts.model.Firestations;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.model.Persons;
import com.safetynet.safetynetalerts.repository.FireStationsRepository;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import com.safetynet.safetynetalerts.service.FireStationService;
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
public class FireStationServiceTest {


    private static FireStationService fireStationService;
    @Mock
    private static FireStationsRepository fireStationRepository;
    @Mock
    private static PersonRepository personRepository;
    @Mock
    private static MedicalRecordRepository medicalRecordRepository;
    private ArrayList<Firestations> fireStationList;

    @BeforeEach
    void setup() {
        fireStationService = new FireStationService(fireStationRepository, personRepository, medicalRecordRepository);
        ArrayList<Persons> personList = new ArrayList<>();
        Persons person = new Persons("john", "doe", "42 rue des ponts", "metz", "12345", "12345678910", "johndoe@gmail.com");
        personList.add(person);
        ArrayList<Medicalrecords> medicalList = new ArrayList<>();
        Medicalrecords medicalRecord = new Medicalrecords("john", "doe", "01/02/1999", List.of(""), List.of(""));
        medicalList.add(medicalRecord);
        fireStationList = new ArrayList<>();
        Firestations fireStation = new Firestations("42 rue des ponts", "1");
        fireStationList.add(fireStation);

        when(fireStationRepository.getFireStationList()).thenReturn(fireStationList);
        when(personRepository.getPersonList()).thenReturn(personList);
        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalList);


    }

    @AfterEach
    void clearList() {
        fireStationList.clear();
    }

    @Test
    public void getFireStationListTest() {
        assertEquals(1, fireStationService.getFireStation().size());
    }

    @Test
    public void addFireStationToListTest() {
        Firestations fireStation = new Firestations("rue de la republique", "2");
        fireStationService.addFireStation(fireStation);
        verify(fireStationRepository, times(1)).addFireStation(fireStation);
    }

    @Test
    public void deleteFireStationToList() {
        Firestations fireStation = new Firestations("rue de la republique", "2");
        fireStationService.deleteFireStation(fireStation);
        verify(fireStationRepository, times(1)).deleteFireStation(fireStation);
    }

    @Test
    public void findFireStationByAddressTest() {
        Firestations fireStation = new Firestations("rue de la republique", "2");
        fireStationService.addFireStation(fireStation);

        fireStationService.findFireStation("rue de la republique");
        verify(fireStationRepository, times(1)).findFireStation("rue de la republique");
    }

    @Test
    public void updateFireStationTest() {
        Firestations fireStation = new Firestations("rue de la republique", "2");
        fireStationService.addFireStation(fireStation);

        Firestations fireStation1 = new Firestations("rue de la republique", "2");
        fireStationService.updateFireStation(fireStation1, "rue de la re");
        verify(fireStationRepository, times(1)).updateFireStation(fireStation1, "rue de la re");
    }

    @Test
    public void getPersonByStation() {
        fireStationService.personsByStation("1");
        verify(fireStationRepository, times(1)).getFireStationList();
    }

    @Test
    public void getFireStationByPersonAddressTest() {
        assertEquals(1, fireStationService.fireStationByPersonAddress("42 rue des ponts").size());
    }

    @Test
    public void getPhoneByFireStationTest() {
        assertEquals(1, fireStationService.phoneByFireStation("1").size());
    }

    @Test
    public void getListOfPersonByStationTest() {
        assertEquals(1, fireStationService.listOfPersonsByStation("1").size());
    }

    @Test
    public void getFirestationWithCount() {
        assertEquals(1, fireStationService.fireStationWithCount("1").size());
    }
}
