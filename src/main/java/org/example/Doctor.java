package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User{

    private String ward;

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

    public void runDoctor(List<Doctor> doctors,List<Patient> patients, Scanner scanner, FileUtils fileUtils) throws ParseException {

        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : View all patients");
            System.out.println("3 : update patient medical report");
            System.out.println("4 : Logout");
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
                    Patient updatedPatient = updateMedicalReport(patients, scanner);
                    if (updatedPatient != null) {
                        fileUtils.patientUpdate(updatedPatient, patients);
                    }
                }case "4" -> {
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

        System.out.println("Enter a note: ");
        String note = scanner.nextLine();

        if(medicineName.isEmpty() || dosage.isEmpty() || note.isEmpty()){
            System.out.println("Invalid input");
            return null;
        }

        patient.getMedications().getMedicines().put(medicineName, dosage);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date());
        patient.getMedications().getMedicines().put(medicineName, dosage);
        patient.getMedications().getNotes().put(formattedDate, note);

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

}
