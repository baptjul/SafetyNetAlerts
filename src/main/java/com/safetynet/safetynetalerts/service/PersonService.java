package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.AdultsListDTO;
import com.safetynet.safetynetalerts.DTO.ChildrenListDTO;
import com.safetynet.safetynetalerts.DTO.PersonInfoDTO;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.model.Persons;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


@Service
public class PersonService {

    private final static Logger logger = LogManager.getLogger("PersonService");
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public PersonService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public ArrayList<Persons> getPersons() {
        logger.info("getPersons service");
        return personRepository.getPersonList();
    }

    public ArrayList<Persons> addPerson(Persons persons) {
        logger.info("addPerson service");
        return personRepository.addPerson(persons);
    }

    public ArrayList<Persons> deletePerson(Persons persons) {
        logger.info("deletePerson service");
        return personRepository.deletePerson(persons);
    }

    public Persons findPerson(String firstName, String lastName) {
        logger.info("findPersonByName service");
        return personRepository.findPerson(firstName, lastName);
    }

    public Persons updatePerson(Persons persons, String firstName, String lastName) {
        logger.info("updatePerson service");
        return personRepository.updatePerson(persons, firstName, lastName);
    }

    public ArrayList<String> getMailPersons(String city) {
        logger.info("getMailPersonsByCity service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<String> getPersonsMail = new ArrayList<>();
        for (Persons persons : personsList) {
            if (persons.getCity().contains(city)) {
                getPersonsMail.add(persons.getFirstName() + persons.getLastName() + ' ' + ':' + ' ' + persons.getEmail());
            }
        }
        return getPersonsMail;
    }

    public ArrayList<PersonInfoDTO> personListMedication(String firstname, String lastname) {
        logger.info("listMedicationByName service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Medicalrecords> medicalRecordsList = medicalRecordRepository.getMedicalRecordList();
        ArrayList<PersonInfoDTO> medicationOfPersons = new ArrayList<>();

        for (Persons persons : personsList) {
            if (persons.getLastName().contains(lastname) && (persons.getFirstName().contains(firstname))) {
                for (Medicalrecords medicalrecords : medicalRecordsList) {
                    if (medicalrecords.getLastName().contains(persons.getLastName())) {
                        medicationOfPersons.add(new PersonInfoDTO(medicalrecords.getFirstName(), medicalrecords.getLastName(), persons.getAddress(), persons.getCity(), persons.getZip(), persons.getEmail(), medicalrecords.getBirthdate(), medicalrecords.getMedications(), medicalrecords.getAllergies()));
                    }
                }
            }
        }
        return medicationOfPersons;
    }


    public ArrayList<AdultsListDTO> adultList(String address) {
        logger.info("adultList service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Medicalrecords> medicalRecordsList = medicalRecordRepository.getMedicalRecordList();
        ArrayList<AdultsListDTO> adultsListByAddress = new ArrayList<>();

        for (Persons persons : personsList) {
            if (persons.getAddress().contains(address)) {
                for (Medicalrecords medicalrecords : medicalRecordsList) {
                    if (medicalrecords.getFirstName().contains(persons.getFirstName()) && medicalrecords.getLastName().contains(persons.getLastName())) {
                        String strDate = medicalrecords.getBirthdate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate date = LocalDate.parse(strDate, formatter);
                        LocalDate currentDate = LocalDate.now();
                        if ((ChronoUnit.YEARS.between(date, currentDate)) > 18) {
                            adultsListByAddress.add(new AdultsListDTO(persons.getFirstName(), persons.getLastName()));
                        }
                    }
                }
            }
        }
        return adultsListByAddress;
    }


    public ArrayList<ChildrenListDTO> childrenList(String address) {
        logger.info("childrenList service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Medicalrecords> medicalRecordsList = medicalRecordRepository.getMedicalRecordList();
        ArrayList<AdultsListDTO> adultsList = adultList(address);
        ArrayList<ChildrenListDTO> childListByAddress = new ArrayList<>();

        for (Persons persons : personsList) {
            if (persons.getAddress().contains(address)) {
                for (Medicalrecords medicalrecords : medicalRecordsList) {
                    if (medicalrecords.getFirstName().contains(persons.getFirstName()) && medicalrecords.getLastName().contains(persons.getLastName())) {
                        String strDate = medicalrecords.getBirthdate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate date = LocalDate.parse(strDate, formatter);
                        LocalDate currentDate = LocalDate.now();
                        if ((ChronoUnit.YEARS.between(date, currentDate)) < 18) {
                            childListByAddress.add(new ChildrenListDTO(persons.getFirstName(), persons.getLastName(), medicalrecords.getBirthdate(), adultsList));
                        }
                    }
                }
            }
        }
        return childListByAddress;
    }
}

