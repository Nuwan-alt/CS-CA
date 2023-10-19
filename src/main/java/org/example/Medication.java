package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Medication {
    private String decease;
    private HashMap<String,String> medicines;
    private HashMap<String,String> notes;

    public Medication(String decease) {
        this.decease = decease;
        this.medicines = new HashMap<>();
        this.notes = new HashMap<>();
    }

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

    public HashMap<String, String> getNotes() {
        return notes;
    }

    public void setNotes(HashMap<String, String> notes) {
        this.notes = notes;
    }

}
