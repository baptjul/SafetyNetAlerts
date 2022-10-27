package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.DataSource;
import com.safetynet.safetynetalerts.model.Firestations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FireStationsRepository {

    @Autowired
    private DataSource dataSource;

    private final static Logger logger = LogManager.getLogger("PersonRepository");

    public ArrayList<Firestations> getFireStationList() {
        logger.info("GetFireStationList access");
        return (ArrayList<Firestations>) dataSource.getFirestations();
    }

    public ArrayList<Firestations> addFireStation(Firestations fireStations) {
        logger.info("AddFireStation access, fireStation: {}", fireStations);
        ArrayList<Firestations> addFirestationsList = getFireStationList();
        addFirestationsList.add(fireStations);
        dataSource.setFirestations(addFirestationsList);
        return addFirestationsList;
    }

    public ArrayList<Firestations> deleteFireStation(Firestations fireStations, String address) {
        logger.info("DeleteFireStation access, fireStation: {}, address: {}", fireStations, address );
        ArrayList<Firestations> deleteFirestationsList = getFireStationList();
        for (Firestations fireStationsToDel: deleteFirestationsList) {
            if (fireStationsToDel.getAddress().contains(address)) {
                deleteFirestationsList.remove(fireStationsToDel);
                dataSource.setFirestations(deleteFirestationsList);
                return deleteFirestationsList;
            }
        }
        return deleteFireStation(fireStations, address);
    }

    public Firestations findFireStation(String address) {
        logger.info("FindFireStation access, address: {}", address);
        ArrayList<Firestations> fireStations = getFireStationList();
        for (Firestations fireStation: fireStations) {
            if (fireStation.getAddress().contains(address)) {
                return fireStation;
            }
        }
        return findFireStation(address);
    }

    public ArrayList<Firestations> updateFireStation(Firestations fireStation, String address) {
        logger.info("UpdateFireStation access, fireStation: {}, address: {}", fireStation, address);
        ArrayList<Firestations> allFireStations = getFireStationList();
        for (Firestations fireStations: allFireStations) {
            if (fireStations.getAddress().contains(address)) {
                fireStations.setStation(fireStation.getStation());
                dataSource.setFirestations(allFireStations);
                ArrayList<Firestations> allUpdatedFireStations = getFireStationList();
                return allUpdatedFireStations;
            }
        }
        return updateFireStation(fireStation, address);
    }


}
