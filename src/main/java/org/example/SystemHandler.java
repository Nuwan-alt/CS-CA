package org.example;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemHandler {
    private Integer userId;
    private String role;
    private String userPassword;
    private String username;
    private List<Doctor> doctors;
    private List<Staff> staffs;
    private List<Patient> patients;
    private PasswordUtils passwordUtils;
    private Admin admin;

    List<User> userList = new ArrayList<>();

    public SystemHandler(  String username,String userPassword, List<Doctor> doctors, List<Patient> patients, List<Staff> staffs, Admin admin) {
        this.userPassword = userPassword;
        this.username = username.toLowerCase().trim();
        this.doctors = doctors;
        this.patients = patients;
        this.staffs = staffs;
        this.admin = admin;

        userList.addAll(doctors);
        userList.addAll(patients);
        userList.addAll(staffs);
        userList.add(admin);
        this.passwordUtils = new PasswordUtils();
    }
    public void runSystem(Scanner scanner, FileUtils fileUtils) throws ParseException {
        boolean isLogged = this.login();
        if(isLogged) {
            switch (role.toLowerCase()) {
                case "doctor" -> {
                    Doctor doctor = (Doctor) doctors.stream()
                            .filter(user -> user.getId().equals(userId))
                            .findFirst()
                            .orElse(null);

                    assert doctor != null;
                    doctor.runDoctor(doctors, patients,scanner,fileUtils);

                }
                case "staff" -> {
                    Staff staff = (Staff) staffs.stream()
                            .filter(user -> user.getId().equals(userId))
                            .findFirst()
                            .orElse(null);

                    assert staff != null;
                    staff.runStaff(doctors,scanner);
                }
                case "patient" -> {
                    Patient patient = patients.stream()
                            .filter(user -> user.getId().equals(userId))
                            .findFirst()
                            .orElse(null);

                    assert patient != null;
                    patient.runPatients(doctors,scanner);
                }case "admin" -> {
                    admin.runAdmin(doctors,patients,staffs,scanner,fileUtils);
                }
            }
        }
    }

    private boolean login(){
//        System.out.println(passwordUtils.createPassword("Mark"));

        boolean isLogged = false;
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username) &&  passwordUtils.checkPasswordMD5(userPassword, user.getPassword())) {
                this.userId = user.getId();
                this.role = user.getRole();
                isLogged = true;
                break;
            }
        }

        if(isLogged){
            switch (role.toLowerCase()) {
                case "doctor" -> System.out.println("Welcome Doctor " + username);
                case "staff" -> System.out.println("Welcome Staff Member " + username);
                case "patient" -> System.out.println("Welcome " + username);
                case "admin" -> System.out.println("Welcome Admin " + username);
            }

        }else {
            System.out.println("Invalid credentials");
        }
        return isLogged;
    }

}
