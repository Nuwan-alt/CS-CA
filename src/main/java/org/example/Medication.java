package org.example;

import java.util.HashMap;

public class Medication {
    private String decease;
    private HashMap<String,String> drug_prescriptions;
    private HashMap<String,String> lab_tests;
    

    public Medication(String decease) {
        this.decease = decease;
        this.drug_prescriptions = new HashMap<>();
        this.lab_tests = new HashMap<>();
    }

    public String getDecease() {
        return decease;
    }

    public void setDecease(String decease) {
        this.decease = decease;
    }

    public HashMap<String, String> getMedicines() {
        return drug_prescriptions;
    }

    public void setMedicines(HashMap<String, String> medicines) {
        this.drug_prescriptions = medicines;
    }

    public HashMap<String, String> getLab_tests() {
        return lab_tests;
    }

    public void setLab_tests(HashMap<String, String> lab_tests) {
        this.lab_tests = lab_tests;
    }

}
