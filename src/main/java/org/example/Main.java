package org.example;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        FileUtils fileUtils = new FileUtils();

        fileUtils.printWelcome();

        List <Doctor> doctors = fileUtils.readDoctors();
        List <Staff> staff = fileUtils.readStaff();
        List <Patient> patients = fileUtils.readPatients();
        Admin admin = fileUtils.readAdmin();
        while (true){
            System.out.println("Enter your username:");
            String name = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            SystemHandler systemHandler = new SystemHandler(name, password, doctors, patients, staff,admin);
            systemHandler.runSystem(scanner, fileUtils);
        }

    }

}