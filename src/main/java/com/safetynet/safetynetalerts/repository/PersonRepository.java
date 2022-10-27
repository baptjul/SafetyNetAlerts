package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.DataSource;
import com.safetynet.safetynetalerts.model.Firestations;
import com.safetynet.safetynetalerts.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository

public class PersonRepository {

    @Autowired
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

    public ArrayList<Persons> deletePerson(String firstName, String lastName) {
        logger.info("DeletePerson access, firstName: {}, lastName: {}", firstName, lastName);
        ArrayList<Persons> deletePersonsOfList = getPersonList();
        for (Persons personsToDel : deletePersonsOfList) {
            if (personsToDel.getLastName().contains(lastName) && personsToDel.getFirstName().contains(firstName)) {
                deletePersonsOfList.remove(personsToDel);
                dataSource.setPersons(deletePersonsOfList);
                return deletePersonsOfList;
            }
        }
        return deletePerson(firstName, lastName);
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

    public ArrayList<Persons> updatePerson(Persons person, String firstName, String lastName) {
        logger.info("UpdatePerson access, firstName: {}, lastName: {}", firstName, lastName);
        ArrayList<Persons> personsList = getPersonList();
        for (Persons persons : personsList) {
            if (persons.getLastName().contains(lastName) && persons.getFirstName().contains(firstName)) {
                persons.setAddress(person.getAddress());
                persons.setCity(person.getCity());
                persons.setZip(person.getZip());
                persons.setPhone(person.getPhone());
                persons.setEmail(person.getEmail());
                dataSource.setPersons(personsList);
                ArrayList<Persons> allUpdatedPersonsList = getPersonList();
                return allUpdatedPersonsList;
            }
        }
        return updatePerson(person, firstName, firstName);
    }

}

