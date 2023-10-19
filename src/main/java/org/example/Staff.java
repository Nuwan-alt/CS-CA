package org.example;

import java.util.List;
import java.util.Scanner;

public class Staff extends User{

    public Staff(int i, String username,String role, Integer age) {
        super(i, username, role, age);
    }

    public void runStaff(List<Doctor> doctors, Scanner scanner) {

        while (true){
            System.out.println("==================== Enter your choice ====================");
            System.out.println("1 : view all doctors");
            System.out.println("2 : Logout");
            System.out.println("===========================================================");

            String doctorUsername = scanner.nextLine();

            switch (doctorUsername) {
                case "1" -> {
                    this.viewAllDoctors(doctors);
                }
                case "2" -> {
                    System.out.println("Logged out");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

}
