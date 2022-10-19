package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.DataSource;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;

@Repository
public class MedicalRecordRepository { //extends JsonReadFileRepository

    @Autowired
//    @Qualifier("DataSource")
    private DataSource dataSource;

    private final static Logger logger = LogManager.getLogger("MedicalRecordRepository");

    public ArrayList<Medicalrecords> getMedicalRecordList() {
        logger.info("GetMedicalRecordList access");
        return (ArrayList<Medicalrecords>) dataSource.getMedicalrecords();
        //return this.readMedicalRecordsList();
    }

    public ArrayList<Medicalrecords> addMedicalRecord(Medicalrecords medicalrecords) {
        logger.info("AddMedicalRecord access, medicalRecord: {}", medicalrecords);
        ArrayList<Medicalrecords> addMedicalrecordsList = getMedicalRecordList();
        addMedicalrecordsList.add(medicalrecords);
        return addMedicalrecordsList;
    }

    public ArrayList<Medicalrecords> deleteMedicalRecord(Medicalrecords medicalrecords) {
        logger.info("DeleteMedicalRecord access, medicalRecord: {}", medicalrecords);
        ArrayList<Medicalrecords> deleteMedicalrecordsList = getMedicalRecordList();
        deleteMedicalrecordsList.remove(medicalrecords);
        return deleteMedicalrecordsList;
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

    public Medicalrecords updateMedicalRecord(Medicalrecords medicalrecords, String firstName, String lastName) {
        logger.info("UpdateMedicalRecord access, medicalRecord: {}, firstName: {}, lastName: {}", medicalrecords, firstName, lastName);
        ArrayList<Medicalrecords> medicalRecordsName = getMedicalRecordList();
        for (Medicalrecords medicalRecords: medicalRecordsName) {
            if (medicalRecords.getFirstName().contains(firstName) && medicalrecords.getLastName().contains(lastName)) {
                medicalRecords.setFirstName(medicalrecords.getFirstName());
                medicalRecords.setBirthdate(medicalrecords.getBirthdate());
                medicalRecords.setMedications(medicalrecords.getMedications());
                medicalRecords.setAllergies(medicalrecords.getAllergies());
            }
            return medicalRecords;
        }
        return updateMedicalRecord(medicalrecords, firstName, lastName);
    }
}
