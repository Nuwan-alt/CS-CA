package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class FileUtils {
    Gson gson = new Gson();
    private List<User> workers = null;
    private List<Patient> patients = null;

    public List<User> readWorkers(){
        try {
            FileReader reader1 = new FileReader("src/main/resources/workers.json");

            Type userListType1 = new TypeToken<List<User>>(){}.getType();

            workers = gson.fromJson(reader1, userListType1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workers;
    }

    public List<Patient> readPatients(){
        try {
            FileReader reader1 = new FileReader("src/main/resources/patients.json");

            Type userListType1 = new TypeToken<List<Patient>>(){}.getType();

            patients = gson.fromJson(reader1, userListType1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void welcome(){
        String filePath = "src/main/resources/welcome.txt"; // Replace with the path to your text file

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUser(){

    }


}
