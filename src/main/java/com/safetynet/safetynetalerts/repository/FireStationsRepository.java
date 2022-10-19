package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.DataSource;
import com.safetynet.safetynetalerts.model.Firestations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FireStationsRepository { //extends JsonReadFileRepository

    @Autowired
    //    @Qualifier("DataSource")
    private DataSource dataSource;

    private final static Logger logger = LogManager.getLogger("PersonRepository");

    public ArrayList<Firestations> getFireStationList() {
        logger.info("GetFireStationList access");
        return (ArrayList<Firestations>) dataSource.getFirestations();
        //return this.readFireStationList();
    }

    public ArrayList<Firestations> addFireStation(Firestations fireStations) {
        logger.info("AddFireStation access, fireStation: {}", fireStations);
        ArrayList<Firestations> addFirestationsList = getFireStationList();
        addFirestationsList.add(fireStations);
        return addFirestationsList;
    }

    public ArrayList<Firestations> deleteFireStation(Firestations fireStations) {
        logger.info("DeleteFireStation access, fireStation: {}", fireStations);
        ArrayList<Firestations> deleteFirestationsList = getFireStationList();
        deleteFirestationsList.remove(fireStations);
        return deleteFirestationsList;
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

    public Firestations updateFireStation(Firestations fireStation, String address) {
        logger.info("UpdateFireStation access, fireStation: {}, address: {}", fireStation, address);
        ArrayList<Firestations> allFireStations = getFireStationList();
        for (Firestations fireStations: allFireStations) {
            if (fireStations.getAddress().contains(address)) {
                fireStations.setStation(String.valueOf(fireStation));
            }
            return fireStations;
        }
        return updateFireStation(fireStation, address);
    }


}
