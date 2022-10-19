package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.DataSource;
import com.safetynet.safetynetalerts.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository

public class PersonRepository { //extends JsonReadFileRepository

    @Autowired
//    @Qualifier("DataSource")
    private DataSource dataSource;

    private final static Logger logger = LogManager.getLogger("PersonRepository");

    public ArrayList<Persons> getPersonList() {
        logger.info("GetPersonsList access");
        return (ArrayList<Persons>) dataSource.getPersons();
        //return this.readPersonList();
    }

    public ArrayList<Persons> addPerson(Persons persons) {
        logger.info("AddPerson access, person: {}", persons);
        ArrayList<Persons> addPersonsList = getPersonList();
        addPersonsList.add(persons);
        return addPersonsList;
    }

    public ArrayList<Persons> deletePerson(Persons persons) {
        logger.info("DeletePerson access, person: {}", persons);
        ArrayList<Persons> deletePersonsOfList = getPersonList();
        deletePersonsOfList.remove(persons);
        return deletePersonsOfList;
    }

    public Persons findPerson(String firstName, String lastName) {
        logger.info("FindPersonByName access, firstName: {}, lastName: {}", firstName, lastName);
        ArrayList<Persons> personsListByName = getPersonList();
        for (Persons personsFind : personsListByName) {
            if (personsFind.getFirstName().contains(firstName)) {
                return personsFind;
            }
        }
        return findPerson(firstName, lastName);
    }

    public Persons updatePerson(Persons person, String firstName, String lastName) {
        logger.info("UpdatePerson access, firstName: {}, lastName: {}", firstName, lastName);
        ArrayList<Persons> personsList = getPersonList();
        for (Persons persons : personsList) {
            if (persons.getLastName().contains(lastName) && persons.getFirstName().contains(firstName)) {
                persons.setAddress(persons.getAddress());
                persons.setCity(persons.getCity());
                persons.setZip(persons.getZip());
                persons.setPhone(persons.getPhone());
                persons.setEmail(persons.getEmail());
            }
            return persons;
        }
        return updatePerson(person, firstName, firstName);
    }

}

