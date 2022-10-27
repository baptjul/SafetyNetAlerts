package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class MedicalRecordController {

    private final static Logger logger = LogManager.getLogger("MedicalRecordController");

    @Autowired
    MedicalRecordService medicalRecordService;

    @GetMapping(value = "/medicalrecord")
    public ArrayList<Medicalrecords> getMedicalRecord() {
        logger.info("access GetMedicalRecord");
        return medicalRecordService.getMedicalRecord();
    }

    @GetMapping(value = "/medicalrecord/{firstName}/{lastName}")
    public Medicalrecords getMedicalRecordByName(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("access GetMedicalRecordByName");
        return medicalRecordService.findMedicalRecords(firstName, lastName);
    }

    @PostMapping(value = "/medicalrecord/addmedicalrecord")
    public ArrayList<Medicalrecords> addMedicalRecord(@RequestBody Medicalrecords medicalrecords) {
        logger.info("access AddMedicalRecord");
        return medicalRecordService.addMedicalRecord(medicalrecords);
    }

    @PutMapping(value = "/medicalrecord/{firstName}/{lastName}")
    public ArrayList<Medicalrecords> updateMedicalRecord(@RequestBody Medicalrecords medicalrecords, @PathVariable String firstName, @PathVariable String lastName) {
        logger.info("access UpdateMedicalRecord");
        return medicalRecordService.updateMedicalRecord(medicalrecords, firstName, lastName);
    }

    @DeleteMapping(value = "/medicalrecord/{firstName}/{lastName}")
    public ArrayList<Medicalrecords> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("access DeleteMedicalRecord");
        return medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }

}
