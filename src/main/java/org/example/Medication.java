package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Medication {
    private String decease;
    private HashMap<String,String> medicines;
    private HashMap<Date,String> notes;

    public String getDecease() {
        return decease;
    }

    public void setDecease(String decease) {
        this.decease = decease;
    }

    public HashMap<String, String> getMedicines() {
        return medicines;
    }

    public void setMedicines(HashMap<String, String> medicines) {
        this.medicines = medicines;
    }

    public HashMap<Date, String> getNotes() {
        return notes;
    }

    public void setNotes(HashMap<Date, String> notes) {
        this.notes = notes;
    }

}
