package org.example;

import java.util.List;
import java.util.Scanner;

public class Admin extends User{
    PasswordUtils passwordUtils;


    public Admin() {
        super();
        this.passwordUtils = new PasswordUtils();
    }

    public void runAdmin(List<Doctor> doctors, List<Patient> patients, List<Staff> staffs, Scanner scanner, FileUtils fileUtils) {
        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : View all patients");
            System.out.println("3 : View all staff members");
            System.out.println("4 : Add doctor");
            System.out.println("5 : Remove doctor");
            System.out.println("6 : Add staff member");
            System.out.println("7 : Remove staff member");
            System.out.println("8 : Add patient");
            System.out.println("9 : Remove patient");
            System.out.println("10 : Logout");
            System.out.println("===========================================================");
            String doctorUsername = scanner.nextLine();

            switch (doctorUsername) {
                case "1" -> {
                    viewAllDoctors(doctors);
                }
                case "2" -> {
                    viewAllPatients(patients);
                }
                case "3" -> {
                    viewAllStaffs(staffs);
                }case "4" -> {
                    addDoctor(doctors, scanner, fileUtils);
                }case "5" -> {
                    removeDoctor(doctors, scanner, fileUtils);
                }case "6" -> {
                    addStaff(staffs, scanner, fileUtils);
                }case "7" -> {
                    removeStaffMember(staffs, scanner, fileUtils);
                }case "8" -> {
                    addPatient(patients, scanner, fileUtils);
                }case "9" -> {
                    removePatient(patients, scanner, fileUtils);
                }case "10" -> {
                    fileUtils.printLogOut();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
    private void viewAllPatients(List<Patient> patients) {
        String formattedLine1 = String.format("%-10s %-10s: %s", "ID", "Age", "Patient Name");
        System.out.println(formattedLine1);
        for (Patient patient : patients) {
            String formattedOutput = String.format("%-10s %-10d: %s", patient.getId(), patient.getAge(), patient.getUsername());
            System.out.println(formattedOutput);
        }
    }

    private void viewAllStaffs(List<Staff> staffs) {
        System.out.println("*** Staffs ***");
        String formattedLine1 = String.format("%-10s %-10s: %s", "ID", "Age", "Staff member name");
        System.out.println(formattedLine1);
        for (Staff staff : staffs) {
            String formattedLine2 = String.format("%-10s %-10s: %s", staff.getId(), staff.getAge(), staff.getUsername());
            System.out.println(formattedLine2);
        }
    }

    private void addDoctor(List<Doctor> doctors, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter ward");
        String ward = scanner.nextLine();
        System.out.println("Enter age");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = new Doctor(doctors.size() + 1,username, "doctor", age);
        doctor.setWard(ward);
        doctor.setPassword(passwordUtils.createPassword(password));
        doctors.add(doctor);
        if(fileUtils.manageDoctor(doctors)){
            System.out.println("***************** Doctor added successfully *****************");
        }


    }

    private void removeDoctor( List<Doctor> doctors, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter doctor ID");
        System.out.println("Enter -1 to cancel");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        if(doctorId == -1){
            return;
        }

        Doctor doctor = doctors.stream()
                .filter(user -> user.getId().equals(doctorId))
                .findFirst()
                .orElse(null);

        if (doctor == null) {
            System.out.println("Doctor not found");
        }else {
            doctors.remove(doctor);
            if(fileUtils.manageDoctor(doctors)){
                System.out.println("***************** Doctor removed successfully *****************");
            }
        }
    }

    private void addStaff(List<Staff> staffs, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter age");
        Integer age = scanner.nextInt();
        scanner.nextLine();
        Staff staff = new Staff(staffs.size() + 1, username, "staff", age);
        staff.setPassword(passwordUtils.createPassword(password));
        staffs.add(staff);

        if(fileUtils.manageStaff(staffs)){
            System.out.println("***************** Staff member added successfully *****************");
        }

    }

    private void removeStaffMember(List<Staff> staff, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter staff member ID");
        System.out.println("Enter -1 to cancel");
        int staffID = scanner.nextInt();
        scanner.nextLine();

        if(staffID == -1){
            return;
        }

        Staff staff1 = staff.stream()
                .filter(user -> user.getId().equals(staffID))
                .findFirst()
                .orElse(null);

        if (staff1 == null) {
            System.out.println("Doctor not found");
        }else {
            staff.remove(staff1);
            if(fileUtils.manageStaff(staff)){
                System.out.println("***************** Staff member removed successfully *****************");
            }
        }
    }

    private void addPatient(List<Patient> patients, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter decease");
        String decease = scanner.nextLine();
        System.out.println("Enter age");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        Patient patient = new Patient(patients.size() + 1, username, "patient",age, new Medication(decease));
        patient.setPassword(passwordUtils.createPassword(password));
        patients.add(patient);

        if(fileUtils.managePatient(patients)){
            System.out.println("***************** Patient added successfully *****************");
        }
    }

    private void removePatient(List<Patient> patients, Scanner scanner, FileUtils fileUtils){
        System.out.println("Enter patient ID");
        System.out.println("Enter -1 to cancel");
        int patientID = scanner.nextInt();
        scanner.nextLine();

        if(patientID == -1){
            return;
        }

        Patient patient = patients.stream()
                .filter(user -> user.getId().equals(patientID))
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("Patient not found");
        }else {
            patients.remove(patient);
            if(fileUtils.managePatient(patients)){
                System.out.println("***************** Patient removed successfully *****************");
            }
        }
    }
}
