package com.safetynet.safetynetalerts.DTO;

import java.util.ArrayList;

public class ListByStationNumberWithCountDTO {

    ArrayList<FirestationNumberDTO> firestationNumberDTO;
    int countOfAdults;
    int countOfChildren;

    public ListByStationNumberWithCountDTO(ArrayList<FirestationNumberDTO> firestationNumberDTO, int countOfAdults, int countOfChildren) {
        this.firestationNumberDTO = firestationNumberDTO;
        this.countOfAdults = countOfAdults;
        this.countOfChildren = countOfChildren;
    }

    public ArrayList<FirestationNumberDTO> getFirestationNumberDTO() {
        return firestationNumberDTO;
    }

    public void setFirestationNumberDTO(ArrayList<FirestationNumberDTO> firestationNumberDTO) {
        this.firestationNumberDTO = firestationNumberDTO;
    }

    public int getCountOfAdults() {
        return countOfAdults;
    }

    public void setCountOfAdults(int countOfAdults) {
        this.countOfAdults = countOfAdults;
    }

    public int getCountOfChildren() {
        return countOfChildren;
    }

    public void setCountOfChildren(int countOfChildren) {
        this.countOfChildren = countOfChildren;
    }
}
