package org.example;

import java.util.List;
import java.util.Scanner;

public class Staff extends User{

    public void RunStaff(List<Doctor> doctors, Scanner scanner) {

        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : Logout");
            String doctorUsername = scanner.nextLine();

            switch (doctorUsername) {
                case "1" -> {
                    this.viewDoctors(doctors);
                }
                case "2" -> {
                    System.out.println("Logged out");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }



    }

    private void viewDoctors(List<Doctor> doctors){
        System.out.println("Name       : Ward");
        for (Doctor doctor : doctors) {
            String username = doctor.getUsername();
            String ward = doctor.getWard();
            String formattedOutput = String.format("%-20s : %s", username, ward);
            System.out.println(formattedOutput);
        }
    }
}
