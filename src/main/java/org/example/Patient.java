package org.example;

import java.util.List;
import java.util.Scanner;

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

    public void runPatients(List<Doctor> doctors, Scanner scanner){
        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : View my medical report");
            System.out.println("3 : Logout");
            String doctorUsername = scanner.nextLine();

            switch (doctorUsername) {
                case "1" -> {
                    this.viewAllDoctors(doctors);
                }
                case "2" -> {
                    this.viewMyMedicalReport();
                }
                case "3" -> {
                    System.out.println("Logged out");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void viewAllDoctors(List<Doctor> doctors){
        System.out.println("Name       : Ward");
        for (Doctor doctor : doctors) {
            String username = doctor.getUsername();
            String ward = doctor.getWard();
            String formattedOutput = String.format("%-20s : %s", username, ward);
            System.out.println(formattedOutput);
        }
    }

    private void viewMyMedicalReport(){
        Medication medication = this.getMedications();
        System.out.println("Decease " + medication.getDecease());
        System.out.println("*** Medicine ***" );
        System.out.println("Name : Dosage ");
        medication.getMedicines().forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println("*** Notes ***" );
        System.out.println("Date : Note ");
        medication.getNotes().forEach((key, value) -> System.out.println("Date " +key + " : " + value));
    }

}
