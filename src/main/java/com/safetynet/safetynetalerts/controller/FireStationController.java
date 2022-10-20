package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.DTO.FireStationByPersonAddressDTO;
import com.safetynet.safetynetalerts.DTO.FloodStationDTO;
import com.safetynet.safetynetalerts.DTO.ListByStationNumberWithCountDTO;
import com.safetynet.safetynetalerts.DTO.PhoneAlertDTO;
import com.safetynet.safetynetalerts.model.Firestations;
import com.safetynet.safetynetalerts.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class FireStationController {

    private final static Logger logger = LogManager.getLogger("FireStationController");

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/firestation")
    public ArrayList<Firestations> getFireStation() {
        logger.info("access GetFireStation");
        return fireStationService.getFireStation();
    }

    @GetMapping(value = "/firestation/{address}")
    public Firestations findFireStation(@PathVariable String address) {
        logger.info("access FindFireStation");
        return fireStationService.findFireStation(address);
    }

    // flood : liste des personnes avec medical
    @GetMapping(value = "/flood/stations")
    public ArrayList<FloodStationDTO> getPersonsByFireStation(@RequestParam(value = "stations") String station) {
        logger.info("access PersonByFireStation");
        return fireStationService.personsByStation(station);
    }

    // phoneAlert : liste des numéros de téléphone
    @GetMapping(value = "/phoneAlert")
    public ArrayList<PhoneAlertDTO> getPhoneListByStation(@RequestParam(value = "firestation") String station) {
        logger.info("access PhoneListByStation");
        return fireStationService.phoneByFireStation(station);
    }

    // fire : personnes par adresse
    @GetMapping(value = "/fire")
    public ArrayList<FireStationByPersonAddressDTO> getPersonListWithFireStationNumber(@RequestParam(value = "address") String address) {
        logger.info("access PersonListWithStationNumber");
        return fireStationService.fireStationByPersonAddress(address);
    }

    // firestation : liste des personnes
    @GetMapping(value = "/fireStation")
    public ArrayList<ListByStationNumberWithCountDTO> getPersonsListByStation(@RequestParam(value = "station") String station) {
        logger.info("access PersonListByStation");
        return fireStationService.fireStationWithCount(station);
    }

    @PostMapping(value = "/firestation/addfirestation")
    public ArrayList<Firestations> addFireStation(@RequestBody Firestations fireStations) {
        logger.info("access AddFireStation");
        return fireStationService.addFireStation(fireStations);
    }

    @PutMapping(value = "/firestation/{address}")
    public Firestations updateFireStation(@RequestBody Firestations fireStations, @PathVariable String address) {
        logger.info("access UpdateFireStation");
        return fireStationService.updateFireStation(fireStations, address);
    }

    @DeleteMapping(value = "/firestation")
    public ArrayList<Firestations> deleteFireStation(Firestations fireStations) {
        logger.info("access DeleteFireStation");
        return fireStationService.deleteFireStation(fireStations);
    }
}
