package com.safetynet.safetynetalerts.service;


import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MedicalRecordService {

    private final static Logger logger = LogManager.getLogger("MedicalRecordService");

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public ArrayList<Medicalrecords> getMedicalRecord() {
        logger.info("getMedicalRecord service");
        return medicalRecordRepository.getMedicalRecordList();
    }

    public ArrayList<Medicalrecords> addMedicalRecord(Medicalrecords medicalrecords) {
        logger.info("addMedicalRecord service");
        return medicalRecordRepository.addMedicalRecord(medicalrecords);
    }

    public ArrayList<Medicalrecords> deleteMedicalRecord(Medicalrecords medicalrecords) {
        logger.info("deleteMedicalRecord service");
        return medicalRecordRepository.deleteMedicalRecord(medicalrecords);
    }

    public Medicalrecords findMedicalRecords(String firstName, String lastName) {
        logger.info("findMedicalRecords service");
        return medicalRecordRepository.findMedicalRecords(firstName, lastName);
    }

    public Medicalrecords updateMedicalRecord(Medicalrecords medicalrecords, String firstName, String lastName) {
        logger.info("updateMedicalRecord service");
        return medicalRecordRepository.updateMedicalRecord(medicalrecords, firstName, lastName);
    }

}
