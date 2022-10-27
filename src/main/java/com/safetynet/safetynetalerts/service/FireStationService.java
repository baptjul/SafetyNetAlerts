package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.DTO.*;
import com.safetynet.safetynetalerts.model.Firestations;
import com.safetynet.safetynetalerts.model.Medicalrecords;
import com.safetynet.safetynetalerts.model.Persons;
import com.safetynet.safetynetalerts.repository.FireStationsRepository;
import com.safetynet.safetynetalerts.repository.MedicalRecordRepository;
import com.safetynet.safetynetalerts.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class FireStationService {

    private final static Logger logger = LogManager.getLogger("FireStationService");

    @Autowired
    FireStationsRepository fireStationsRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public FireStationService(FireStationsRepository fireStationsRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.fireStationsRepository = fireStationsRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public ArrayList<Firestations> getFireStation() {
        logger.info("getFireStation service");
        return fireStationsRepository.getFireStationList();
    }

    public ArrayList<Firestations> addFireStation(Firestations fireStations) {
        logger.info("addFireStation service");
        return fireStationsRepository.addFireStation(fireStations);
    }

    public ArrayList<Firestations> deleteFireStation(Firestations fireStations, String address) {
        logger.info("fireStation service");
        return fireStationsRepository.deleteFireStation(fireStations, address);
    }

    public Firestations findFireStation(String address) {
        logger.info("findFireStation service");
        return fireStationsRepository.findFireStation(address);
    }

    public ArrayList<Firestations> updateFireStation(Firestations fireStations, String address) {
        logger.info("updateFireStation service");
        return fireStationsRepository.updateFireStation(fireStations, address);
    }

    public ArrayList<FloodStationDTO> personsByStation(List<String> stations) {
        logger.info("personListByStation service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Firestations> firestationsList = fireStationsRepository.getFireStationList();
        ArrayList<Medicalrecords> medicalRecordsList = medicalRecordRepository.getMedicalRecordList();
        ArrayList<FloodStationDTO> personsListByStation = new ArrayList<>();

        for (String selectedFireStations : stations) {
            for (Firestations fireStations : firestationsList) {
                if (fireStations.getStation().contains(selectedFireStations)) {
                    String fireStationAddress = fireStations.getAddress();
                    for (Persons persons : personsList) {
                        if (persons.getAddress().contains(fireStationAddress)) {
                            for (Medicalrecords medicalrecords : medicalRecordsList) {
                                if (medicalrecords.getFirstName().contains(persons.getFirstName()) && medicalrecords.getLastName().contains(persons.getLastName())) {
                                    personsListByStation.add(new FloodStationDTO(persons.getFirstName(), persons.getLastName(), persons.getAddress(), persons.getPhone(), medicalrecords.getBirthdate(), medicalrecords.getMedications(), medicalrecords.getAllergies()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return personsListByStation;
    }

    public ArrayList<FireStationByPersonAddressDTO> fireStationByPersonAddress(String address) {
        logger.info("fireStationByPersonAddress service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Firestations> firestationsList = fireStationsRepository.getFireStationList();
        ArrayList<Medicalrecords> medicalRecordsList = medicalRecordRepository.getMedicalRecordList();
        ArrayList<FireStationByPersonAddressDTO> listOfPersonsByAddress = new ArrayList<>();

        for (Persons persons : personsList) {
            if (persons.getAddress().contains(address)) {
                for (Medicalrecords medicalrecords : medicalRecordsList) {
                    if (medicalrecords.getFirstName().contains(persons.getFirstName()) && medicalrecords.getLastName().contains(persons.getLastName())) {
                        for (Firestations fireStations : firestationsList) {
                            if (fireStations.getAddress().contains(persons.getAddress())) {
                                listOfPersonsByAddress.add(new FireStationByPersonAddressDTO(persons.getFirstName(), persons.getLastName(), persons.getPhone(), medicalrecords.getBirthdate(), medicalrecords.getMedications(), medicalrecords.getAllergies(), fireStations.getStation()));
                            }
                        }
                    }
                }
            }
        }
        return listOfPersonsByAddress;
    }

    public ArrayList<PhoneAlertDTO> phoneByFireStation(String station) {
        logger.info("phoneListByStation service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Firestations> firestationsList = fireStationsRepository.getFireStationList();
        ArrayList<PhoneAlertDTO> phoneListByStation = new ArrayList<>();

        for (Firestations fireStations : firestationsList) {
            if (fireStations.getStation().contains(station)) {
                String fireStationAddress = fireStations.getAddress();
                for (Persons persons : personsList) {
                    if (persons.getAddress().contains(fireStationAddress)) {
                        phoneListByStation.add(new PhoneAlertDTO(persons.getPhone()));
                    }
                }
            }
        }
        return phoneListByStation;
    }

    public ArrayList<FirestationNumberDTO> listOfPersonsByStation(String station) {
        logger.info("listOfPersonsByStation service");
        ArrayList<Persons> personsList = personRepository.getPersonList();
        ArrayList<Firestations> firestationsList = fireStationsRepository.getFireStationList();
        ArrayList<FirestationNumberDTO> listOfPersonsByStationNumber = new ArrayList<>();
        for (Firestations fireStations : firestationsList) {
            if (fireStations.getStation().contains(station)) {
                String fireStationAddress = fireStations.getAddress();
                for (Persons persons : personsList) {
                    if (persons.getAddress().contains(fireStationAddress)) {
                        listOfPersonsByStationNumber.add(new FirestationNumberDTO(persons.getFirstName(), persons.getLastName(), persons.getAddress(), persons.getCity(), persons.getZip(), persons.getPhone()));
                    }
                }
            }
        }
        return listOfPersonsByStationNumber;
    }

    public ArrayList<ListByStationNumberWithCountDTO> fireStationWithCount(String station) {
        logger.info("listOfPersonsByStationWithAdultAndChildrenCount service");
        ArrayList<FirestationNumberDTO> listOfPerson = listOfPersonsByStation(station);
        ArrayList<Medicalrecords> medicalRecordsList = medicalRecordRepository.getMedicalRecordList();
        ArrayList<ListByStationNumberWithCountDTO> listOfPersonByStationWithCount = new ArrayList<>();
        ArrayList<String> adultCount = new ArrayList<>();
        ArrayList<String> childrenCount = new ArrayList<>();
        for (FirestationNumberDTO person : listOfPerson) {
            for (Medicalrecords medicalrecords : medicalRecordsList) {
                if (medicalrecords.getFirstName().contains(person.getFirstname())) {
                    String strDate = medicalrecords.getBirthdate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate date = LocalDate.parse(strDate, formatter);
                    LocalDate currentDate = LocalDate.now();
                    if ((ChronoUnit.YEARS.between(date, currentDate)) < 18) {
                        childrenCount.add(medicalrecords.getFirstName());
                    } else {
                        adultCount.add(medicalrecords.getFirstName());
                    }
                }

            }

        }
        listOfPersonByStationWithCount.add(new ListByStationNumberWithCountDTO(listOfPerson, adultCount.size(), childrenCount.size()));
        return listOfPersonByStationWithCount;
    }
}