package com.safetynet.safetynetalerts.serviceTets;

import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
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
public class MedicalRecordServiceTest {


    private static MedicalRecordService medicalRecordService;
    @Mock
    private static MedicalRecordRepository medicalRecordRepository;
    private ArrayList<Medicalrecords> medicalList;

    @BeforeEach
    void setup() {
        medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        medicalList = new ArrayList<>();
        Medicalrecords medicalRecord = new Medicalrecords("john", "doe", "01/02/1999", List.of(""), List.of(""));
        medicalList.add(medicalRecord);

        when(medicalRecordRepository.getMedicalRecordList()).thenReturn(medicalList);
    }

    @AfterEach
    void clearList() {
        medicalList.clear();
    }

    @Test
    public void getMedicalRecordListTest() {
        assertEquals(1, medicalRecordService.getMedicalRecord().size());
    }

    @Test
    public void addMedicalRecordTest() {
        Medicalrecords medicalRecord = new Medicalrecords("jane", "doe", "02/03/2000", List.of(""), List.of(""));
        when(medicalRecordRepository.addMedicalRecord(medicalRecord)).thenReturn(medicalList);
        medicalRecordService.addMedicalRecord(medicalRecord);
        verify(medicalRecordRepository, times(1)).addMedicalRecord(medicalRecord);
    }

    @Test
    public void deleteMedicalRecordTest() {
        Medicalrecords medicalRecord = new Medicalrecords("jane", "doe", "02/03/2000", List.of(""), List.of(""));
        when(medicalRecordRepository.deleteMedicalRecord("john", "doe")).thenReturn(medicalList);
        medicalRecordService.deleteMedicalRecord("john", "doe");
        verify(medicalRecordRepository, times(1)).deleteMedicalRecord("john", "doe");
    }

    @Test
    public void findMedicalRecordByNameTest() {
        Medicalrecords medicalRecord = new Medicalrecords("john", "doe", "01/02/1999", List.of(""), List.of(""));
        when(medicalRecordRepository.findMedicalRecords("john", "doe")).thenReturn(medicalRecord);
        medicalRecordService.findMedicalRecords("john", "doe");
        verify(medicalRecordRepository, times(1)).findMedicalRecords("john", "doe");
    }

    @Test
    public void updateMedicalRecordTest() {
        Medicalrecords medicalRecord = new Medicalrecords("john", "john", "01/02/19993", List.of("aspirine"), List.of(""));
        when(medicalRecordRepository.updateMedicalRecord(medicalRecord, "john", "doe")).thenReturn(medicalList);
        medicalRecordService.updateMedicalRecord(medicalRecord, "john", "doe");
        verify(medicalRecordRepository, times(1)).updateMedicalRecord(medicalRecord, "john", "doe");
    }
}
