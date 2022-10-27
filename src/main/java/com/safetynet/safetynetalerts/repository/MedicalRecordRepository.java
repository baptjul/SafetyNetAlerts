package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.DataSource;
import com.safetynet.safetynetalerts.model.Firestations;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MedicalRecordRepository {

    @Autowired
    private DataSource dataSource;

    private final static Logger logger = LogManager.getLogger("MedicalRecordRepository");

    public ArrayList<Medicalrecords> getMedicalRecordList() {
        logger.info("GetMedicalRecordList access");
        return (ArrayList<Medicalrecords>) dataSource.getMedicalrecords();
    }

    public ArrayList<Medicalrecords> addMedicalRecord(Medicalrecords medicalrecords) {
        logger.info("AddMedicalRecord access, medicalRecord: {}", medicalrecords);
        ArrayList<Medicalrecords> addMedicalrecordsList = getMedicalRecordList();
        addMedicalrecordsList.add(medicalrecords);
        return addMedicalrecordsList;
    }

    public ArrayList<Medicalrecords> deleteMedicalRecord(String firstName, String lastName) {
        logger.info("DeleteMedicalRecord access, firstName: {}, lastName: {}", firstName, lastName);
        ArrayList<Medicalrecords> deleteMedicalrecordsList = getMedicalRecordList();
        for (Medicalrecords medicalRecords: deleteMedicalrecordsList) {
            if (medicalRecords.getFirstName().contains(firstName) && medicalRecords.getLastName().contains(lastName)) {
                deleteMedicalrecordsList.remove(medicalRecords);
                dataSource.setMedicalrecords(deleteMedicalrecordsList);
                return deleteMedicalrecordsList;
            }
        }
        return deleteMedicalRecord(firstName, lastName);
    }

    public Medicalrecords findMedicalRecords(String firstName, String lastName) {
        logger.info("FindMedicalRecords access, firstName: {}, lastName: {}", firstName, lastName);
        ArrayList<Medicalrecords> medicalRecordsName = getMedicalRecordList();
        for (Medicalrecords medicalrecords : medicalRecordsName) {
            if (medicalrecords.getFirstName().contains(firstName) && medicalrecords.getLastName().contains(lastName)) {
                return medicalrecords;
            }
        }
        return findMedicalRecords(firstName, lastName);
    }

    public ArrayList<Medicalrecords> updateMedicalRecord(Medicalrecords medicalrecords, String firstName, String lastName) {
        logger.info("UpdateMedicalRecord access, medicalRecord: {}, firstName: {}, lastName: {}", medicalrecords, firstName, lastName);
        ArrayList<Medicalrecords> medicalRecordsName = getMedicalRecordList();
        for (Medicalrecords medicalRecords: medicalRecordsName) {
            if (medicalRecords.getFirstName().contains(firstName) && medicalRecords.getLastName().contains(lastName)) {
                medicalRecords.setBirthdate(medicalrecords.getBirthdate());
                medicalRecords.setMedications(medicalrecords.getMedications());
                medicalRecords.setAllergies(medicalrecords.getAllergies());
                dataSource.setMedicalrecords(medicalRecordsName);
                ArrayList<Medicalrecords> allUpdatedMedicalRecords = getMedicalRecordList();
                return allUpdatedMedicalRecords;
            }
        }
        return updateMedicalRecord(medicalrecords, firstName, lastName);
    }
}
