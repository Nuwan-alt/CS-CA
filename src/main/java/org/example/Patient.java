package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Patient extends User {
    private Medication medications;
    PasswordUtils passwordUtils;

    public Patient(){
        super();
        this.passwordUtils = new PasswordUtils();
    }
    public Patient(Integer id, String username, String role, Integer age, Medication medications) {
        super(id, username, role,age);
        this.medications = medications;
    }
    public Medication getMedications() {
        return this.medications;
    }

    public void runPatients(List<Doctor> doctors,List<Patient> patients, Scanner scanner, FileUtils fileUtils) throws ParseException {
        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : View my medical report");
            System.out.println("3 : change password");
            System.out.println("4 : Logout");
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
                    changePassword(patients, scanner, fileUtils);
                    System.out.println("Password changed successfully");
                    System.out.println("Please login again");
                    return;
                }case "4" -> {
                    fileUtils.printLogOut();
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
        String formattedLine1 = String.format("%-15s : %s", "Drug", "Dosage");
        System.out.println(formattedLine1);

        medication.getMedicines().forEach((name, dosage) -> {
            String formattedLine = String.format("%-15s : %s", name, dosage);
            System.out.println(formattedLine);
        });

        System.out.println("*** Notes ***");
        String formattedLine2 = String.format("%-15s : %s", "Date", "Lab test note");
        System.out.println(formattedLine2);

        medication.getLab_tests().forEach((date, note) -> {
            ;
            String formattedLine = String.format("%-15s : %s", date, note);
            System.out.println(formattedLine);
        });
    }
    private void changePassword(List<Patient> patients, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if(passwordUtils.checkPasswordMD5(currentPassword, this.getPassword())) {
            System.out.println("Enter your new password: ");
            String newPassword = scanner.nextLine();
            this.setPassword(passwordUtils.createPassword(newPassword));
            fileUtils.patientUpdate(this, patients);
        }else {
            System.out.println("Incorrect password");
        }

    }

}
