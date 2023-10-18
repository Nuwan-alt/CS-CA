package org.example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Patient extends User {
    private Medication medications;

    public Patient(){
        super();
    }
    public Patient(Integer id, String username, String role, Medication medications) {
        super(id, username, role);
        this.medications = medications;
    }

    public Medication getMedications() {
        return this.medications;
    }

}
