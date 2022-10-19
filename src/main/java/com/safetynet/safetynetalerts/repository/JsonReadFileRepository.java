//package com.safetynet.safetynetalerts.repository;
//
//import com.safetynet.safetynetalerts.model.Firestations;
//import com.safetynet.safetynetalerts.model.Medicalrecords;
//import com.safetynet.safetynetalerts.model.Persons;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.stereotype.Repository;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//
//
//@Repository
//public class JsonReadFileRepository {
//
//    private static final Logger logger = LogManager.getLogger("JsonReadFileRepository");
//    protected static String filePath = "src/main/resources/json/data.json";
//
//    public ArrayList<Persons> readPersonList() {
//
//        logger.info("LoadPerson access (" + filePath + ")");
//
//        JSONParser personParser = new JSONParser();
//        JSONArray personList = new JSONArray();
//        JSONObject personObject = new JSONObject();
//
//        try {
//            Object obj = personParser.parse(new FileReader(filePath));
//            personObject = (JSONObject) obj;
//            personList = (JSONArray) personObject.get("persons");
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//        }
//
//
//        ArrayList<Persons> listOfPeople = new ArrayList<>();
//        for (Object o : personList) {
//            JSONObject personListValue = (JSONObject) o;
//            listOfPeople.add(new Persons(personListValue.get("firstName").toString(), personListValue.get("lastName").toString(), personListValue.get("address").toString(), personListValue.get("city").toString(), personListValue.get("zip").toString(), personListValue.get("phone").toString(), personListValue.get("email").toString()));
//
//        }
//        return listOfPeople;
//
//
//    }
//
//    public ArrayList<Firestations> readFireStationList() {
//
//        logger.info("LoadFireStation access (" + filePath + ")");
//
//        JSONParser fireStationParser = new JSONParser();
//        JSONArray fireStationList = new JSONArray();
//        JSONObject fireStationObject = new JSONObject();
//
//        try {
//            Object obj = fireStationParser.parse(new FileReader(filePath));
//            fireStationObject = (JSONObject) obj;
//            fireStationList = (JSONArray) fireStationObject.get("firestations");
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<Firestations> listOfFireStations = new ArrayList<>();
//        for (Object o : fireStationList) {
//            JSONObject fireStationListValue = (JSONObject) o;
//            listOfFireStations.add(new Firestations(fireStationListValue.get("address").toString(), fireStationListValue.get("station").toString()));
//        }
//        return listOfFireStations;
//    }
//
//    public ArrayList<Medicalrecords> readMedicalRecordsList() {
//
//        logger.info("LoadMedicalRecord access (" + filePath + ")");
//
//        JSONParser medicalRecordParser = new JSONParser();
//        JSONArray medicalRecordList = new JSONArray();
//        JSONObject medicalRecordObject = new JSONObject();
//
//        try {
//            Object obj = medicalRecordParser.parse(new FileReader(filePath));
//            medicalRecordObject = (JSONObject) obj;
//            medicalRecordList = (JSONArray) medicalRecordObject.get("medicalrecords");
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<Medicalrecords> listOfMdeicalrecords = new ArrayList<>();
//        for (Object o : medicalRecordList) {
//            JSONObject medicalRecordListValue = (JSONObject) o;
//            listOfMdeicalrecords.add(new Medicalrecords(medicalRecordListValue.get("firstName").toString(), medicalRecordListValue.get("lastName").toString(), medicalRecordListValue.get("birthdate").toString(), Collections.singletonList(medicalRecordListValue.get("medications").toString()), Collections.singletonList(medicalRecordListValue.get("allergies").toString())));
//        }
//        return listOfMdeicalrecords;
//    }
//
//
//}