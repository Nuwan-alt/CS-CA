package org.example;

import java.util.List;
import java.util.Scanner;

public class Staff extends User{

    PasswordUtils passwordUtils;

    public Staff(int i, String username,String role, Integer age) {
        super(i, username, role, age);
    }

    public Staff() {
        super();
        this.passwordUtils = new PasswordUtils();
    }

    public void runStaff(List<Doctor> doctors, List<Patient> patients,List<Staff> staff, Scanner scanner, FileUtils fileUtils) {

        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : view patient record");
            System.out.println("3 : view all patients");
            System.out.println("4 : change password");
            System.out.println("5: Logout");
            System.out.println("===========================================================");

            String doctorUsername = scanner.nextLine();

            switch (doctorUsername) {
                case "1" -> {
                    this.viewAllDoctors(doctors);
                }case "2" -> {
                    this.viewPatientRecord(patients, scanner);
                }case "3" -> {
                    this.viewAllPatients(patients);
                }case "4" -> {
                    this.changePassword(staff, scanner, fileUtils);
                    System.out.println("Password changed successfully");
                    System.out.println("Please login again");
                    return;
                }
                case "5" -> {
                    fileUtils.printLogOut();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

    private void viewPatientRecord(List<Patient> patients, Scanner scanner) {
        System.out.println("Enter patient id");
        Integer patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patients.stream()
                .filter(user -> user.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        if(patient == null) {
            System.out.println("Patient not found");
            return;
        }

        System.out.println("Patient name:      " + patient.getUsername());
        System.out.println("Patient age:       " + patient.getAge());
        System.out.println("Patient decease:   " + patient.getMedications().getDecease());
        System.out.println(" ----- Patient drug prescription -----");
        patient.getMedications().getMedicines().forEach((name, dosage) -> {
            String formattedLine = String.format("%-20s : %s", name, dosage);
            System.out.println(formattedLine);
        });
        System.out.println("----- Patient lab report -----" );
        patient.getMedications().getLab_tests().forEach((name, result) -> {
            String formattedLine = String.format("%-20s : %s", name, result);
            System.out.println(formattedLine);
        });

    }

    private void viewAllPatients(List<Patient> patients) {
        String formattedLine1 = String.format("%-10s %-10s: %s", "ID", "Age", "Patient Name");
        System.out.println(formattedLine1);
        for (Patient patient : patients) {
            String formattedOutput = String.format("%-10s %-10d: %s", patient.getId(), patient.getAge(), patient.getUsername());
            System.out.println(formattedOutput);
        }
    }

    private void changePassword(List<Staff> staff, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if(passwordUtils.checkPasswordMD5(currentPassword, this.getPassword())) {
            System.out.println("Enter your new password: ");
            String newPassword = scanner.nextLine();
            this.setPassword(passwordUtils.createPassword(newPassword));
            fileUtils.staffUpdate(this, staff);
        }else {
            System.out.println("Incorrect password");
        }

    }

}
