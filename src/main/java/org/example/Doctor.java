package org.example;

import java.util.Scanner;

public class Doctor extends User{

    private String ward;

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Doctor(Integer id, String username, String role) {
        super(id, username, role);
    }

    public void callDoc(){
        System.out.println("Doctor called");
    }

    public void addMedication(Integer userId, Scanner scanner) {

        System.out.println("Enter the reason for the medication: ");
        String reason = scanner.nextLine();

        System.out.println("Enter the name of the medicine: ");
        String medicineName = scanner.nextLine();

        System.out.println("Enter the dosage of the medicine: ");
        Integer dosage = scanner.nextInt();

        System.out.println("Enter the number of times per day the medicine should be taken: ");
        Integer timesPerDay = scanner.nextInt();

        System.out.println("Enter a note: ");
        String note = scanner.nextLine();
    }




}
