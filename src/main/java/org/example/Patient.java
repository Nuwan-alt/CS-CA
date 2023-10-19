package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Patient extends User {
    private Medication medications;

    public Patient(){
        super();
    }
    public Patient(Integer id, String username, String role, Integer age, Medication medications) {
        super(id, username, role,age);
        this.medications = medications;
    }

    public Medication getMedications() {
        return this.medications;
    }

    public void runPatients(List<Doctor> doctors, Scanner scanner) throws ParseException {
        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : View my medical report");
            System.out.println("3 : Logout");
            System.out.println("===========================================================");
            String doctorUsername = scanner.nextLine();

            switch (doctorUsername) {
                case "1" -> {
                    viewAllDoctors(doctors);
                }
                case "2" -> {
                    viewMyMedicalReport();
                }
                case "3" -> {
                    System.out.println("Logged out");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }


    private void viewMyMedicalReport() {
        Medication medication = this.getMedications();

        String formattedLine3 = String.format("%-15s : %s", "Decease", medication.getDecease());
        System.out.println(formattedLine3);

        System.out.println("*** Medicines ***");
        String formattedLine1 = String.format("%-15s : %s", "Name", "Dosage");
        System.out.println(formattedLine1);

        medication.getMedicines().forEach((name, dosage) -> {
            String formattedLine = String.format("%-15s : %s", name, dosage);
            System.out.println(formattedLine);
        });

        System.out.println("*** Notes ***");
        String formattedLine2 = String.format("%-15s : %s", "Date", "Note");
        System.out.println(formattedLine2);

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        medication.getNotes().forEach((date, note) -> {
            try {
                Date dateObj = inputDateFormat.parse(String.valueOf(date));
                String formattedDate = outputDateFormat.format(dateObj);
                String formattedLine = String.format("%-15s : %s", formattedDate, note);
                System.out.println(formattedLine);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

}
