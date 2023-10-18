package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileUtils fileUtils = new FileUtils();

        fileUtils.welcome();

        List <User> workers = fileUtils.readWorkers();
        List <Patient> patients = fileUtils.readPatients();
        while (true){
            System.out.println("Enter your username:");
            String name = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            SystemHandler systemHandler = new SystemHandler(name, password, workers, patients);
            systemHandler.runSystem();
        }



    }

}