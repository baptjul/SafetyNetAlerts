package com.safetynet.safetynetalerts.DTO;


import java.util.ArrayList;

public class ChildrenListDTO {

    String childLastname;
    String childFirstname;
    String childBirthdate;
    ArrayList<AdultsListDTO> adultsList;

    public ChildrenListDTO(String childLastname, String childFirstname, String childBirthdate, ArrayList<AdultsListDTO> adultsList) {
        this.childLastname = childLastname;
        this.childFirstname = childFirstname;
        this.childBirthdate = childBirthdate;
        this.adultsList = adultsList;
    }

    public String getChildLastname() {
        return childLastname;
    }

    public void setChildLastname(String childLastname) {
        this.childLastname = childLastname;
    }

    public String getChildFirstname() {
        return childFirstname;
    }

    public void setChildFirstname(String childFirstname) {
        this.childFirstname = childFirstname;
    }

    public String getChildBirthdate() {
        return childBirthdate;
    }

    public void setChildBirthdate(String childBirthdate) {
        this.childBirthdate = childBirthdate;
    }

    public ArrayList<AdultsListDTO> getAdultsList() {
        return adultsList;
    }

    public void setAdultsList(ArrayList<AdultsListDTO> adultsList) {
        this.adultsList = adultsList;
    }
}
