package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.DTO.ChildrenListDTO;
import com.safetynet.safetynetalerts.DTO.PersonInfoDTO;
import com.safetynet.safetynetalerts.model.Persons;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class PersonController {

    private final static Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    PersonService personService;

    @GetMapping(value = "/person")
    public ArrayList<Persons> getPersons() {
        logger.info("access GetPersons");
        return personService.getPersons();
    }

    @GetMapping(value = "/person/{firstName}/{lastName}")
    public Persons getPersonByName(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("access GetPersonByName");
        return personService.findPerson(firstName, lastName);
    }

    // communityEmail : liste des mails
    @GetMapping(value = "/communityEmail")
    public ArrayList<String> getPersonsMail(@RequestParam(value = "city") String city) {
        logger.info("access PersonMailByCity");
        return personService.getMailPersons(city);
    }

    // personInfo : liste medical records
    @GetMapping(value = "/personInfo")
    public ArrayList<PersonInfoDTO> getPersonListMedication(@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname) {
        logger.info("access PersonListMedicationBYName");
        return personService.personListMedication(firstname, lastname);
    }

    // childAlert : liste des enfants + habitants
    @GetMapping(value = "/childAlert")
    public ArrayList<ChildrenListDTO> getListOfChildrenByAddress(@RequestParam(value = "address") String address) {
        logger.info("access ListOfChildrenByAddress");
        return personService.childrenList(address);
    }

    @PostMapping(value = "/person/addperson")
    public ArrayList<Persons> addPerson(@RequestBody Persons persons) {
        logger.info("access AddPerson");
        return personService.addPerson(persons);
    }

    @PutMapping(value = "/person/{firstName}/{lastName}")
    public Persons updatePerson(@RequestBody Persons persons, @PathVariable String firstName, @PathVariable String lastName) {
        logger.info("access UpdatePerson");
        return personService.updatePerson(persons, firstName, lastName);
    }

    @DeleteMapping(value = "/person")
    public ArrayList<Persons> deletePerson(Persons persons) {
        logger.info("access DeletePerson");
        return personService.deletePerson(persons);
    }

}
