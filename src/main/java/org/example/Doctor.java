package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User{

    private String ward;

    private PasswordUtils passwordUtils;

//    FileUtils fileUtils = new FileUtils();

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Doctor(Integer id, String username, String role, Integer age) {
        super(id, username, role,age);
    }

    public Doctor(){
        super();
        this.passwordUtils = new PasswordUtils();
    }

    public void runDoctor(List<Doctor> doctors,List<Patient> patients, Scanner scanner, FileUtils fileUtils) throws ParseException {

        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : View all patients");
            System.out.println("3 : view patient record");
            System.out.println("4 : update patient medical report");
            System.out.println("5 : change password");
            System.out.println("6 : Logout");
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
                    viewPatientMedicalReport(patients, scanner);
                }case "4" -> {
                    Patient updatedPatient = updateMedicalReport(patients, scanner);
                    if (updatedPatient != null) {
                        fileUtils.patientUpdate(updatedPatient, patients);
                        System.out.println("Patient medical report updated successfully");
                    }
                }case "5" -> {
                    changePassword(doctors, scanner, fileUtils);
                    System.out.println("Password changed successfully");
                    System.out.println("Please login again");
                    return;
                }
                case "6" -> {
                    fileUtils.printLogOut();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private Patient updateMedicalReport(List<Patient> patients, Scanner scanner) throws ParseException {

        System.out.println("Enter the patient ID: ");
        Integer patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patients.stream()
                .filter(user -> user.getId().equals(patientId))
                .findFirst()
                .orElse(null);
        if (patient == null) {
            System.out.println("Patient not found");
            return null;
        }

        System.out.println("Enter the name of the medicine: ");
        String medicineName = scanner.nextLine();

        System.out.println("Enter the dosage of the medicine: ");
        String dosage = scanner.nextLine();

        System.out.println("Enter a note for the lab test: ");
        String note = scanner.nextLine();

        if(medicineName.isEmpty() || dosage.isEmpty() || note.isEmpty()){
            System.out.println("Invalid input");
            return null;
        }

        patient.getMedications().getMedicines().put(medicineName, dosage);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date());
        patient.getMedications().getMedicines().put(medicineName, dosage);
        patient.getMedications().getLab_tests().put(formattedDate, note);

        return patient;
    }

    private void viewAllPatients(List<Patient> patients) {
        String formattedLine1 = String.format("%-10s %-10s: %s", "ID", "Age", "Patient Name");
        System.out.println(formattedLine1);
        for (Patient patient : patients) {
            String formattedOutput = String.format("%-10s %-10d: %s", patient.getId(), patient.getAge(), patient.getUsername());
            System.out.println(formattedOutput);
        }
    }

    private void viewPatientMedicalReport(List<Patient> patients, Scanner scanner){
        System.out.println("Enter patient ID");
        Integer patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = patients.stream()
                .filter(user -> user.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        if(patient == null) {
            System.out.println("Patient not found");
        }else {
            Medication medication = patient.getMedications();

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

    }

    private void changePassword(List<Doctor> doctors, Scanner scanner, FileUtils fileUtils) {
        System.out.println("Enter your current password: ");
        String currentPassword = scanner.nextLine();

        if(passwordUtils.checkPasswordMD5(currentPassword, this.getPassword())) {
            System.out.println("Enter your new password: ");
            String newPassword = scanner.nextLine();
            this.setPassword(passwordUtils.createPassword(newPassword));
            fileUtils.doctorUpdate(this, doctors);
        }else {
            System.out.println("Incorrect password");
        }

    }

}
