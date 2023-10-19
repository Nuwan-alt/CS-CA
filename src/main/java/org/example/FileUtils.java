package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class FileUtils {
    Gson gson = new Gson();
    private List<Staff> staff = null;
    private List<Patient> patients = null;
    private List<Doctor> doctors = null;

    public List<Staff> readStaff(){
        try {
            FileReader reader1 = new FileReader("src/main/resources/staff.json");

            Type userListType1 = new TypeToken<List<Staff>>(){}.getType();

            staff = gson.fromJson(reader1, userListType1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public Admin readAdmin() {
        Admin admin = null;
        try {
            FileReader reader = new FileReader("src/main/resources/admin.json");
            admin = gson.fromJson(reader, Admin.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void updateAdmin(Admin updatedAdmin) {

        // Step 1: Read the existing admin information
        Admin existingAdmin = readAdmin();

        if (existingAdmin == null) {
            System.out.println("Admin not found for updating.");
            return;
        }

        // Step 2: Update the admin's information
        existingAdmin.setUsername(updatedAdmin.getUsername());
        existingAdmin.setAge(updatedAdmin.getAge());
        // You can update other fields as needed

        // Step 3: Write the updated admin information back to the JSON file
        try (Writer writer = new FileWriter("src/main/resources/admin.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create a Gson instance with pretty printing
            gson.toJson(existingAdmin, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while updating the admin information in the JSON file.");
        }
    }

    public List<Doctor> readDoctors(){
        try {
            FileReader reader2 = new FileReader("src/main/resources/doctors.json");
            Type userListType2 = new TypeToken<List<Doctor>>(){}.getType();

            doctors = gson.fromJson(reader2, userListType2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }
    public List<Patient> readPatients(){
        try {
            FileReader reader3 = new FileReader("src/main/resources/patients.json");
            Type userListType3 = new TypeToken<List<Patient>>(){}.getType();
            patients = gson.fromJson(reader3, userListType3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void patientUpdate(Patient updatedPatient, List<Patient> patients) {

        int index = -1;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(updatedPatient.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            patients.set(index, updatedPatient);
        } else {
            System.out.println("Patient not found for updating.");
            return;
        }

        try (Writer writer = new FileWriter("src/main/resources/patients.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(patients, writer);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while updating the patients list in the JSON file.");
        }
    }

    public void doctorUpdate(Doctor updatedDoctor, List<Doctor> doctors) {

        int index = -1;
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(updatedDoctor.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            doctors.set(index, updatedDoctor);
        } else {
            System.out.println("Doctor not found for updating.");
            return;
        }

        try (Writer writer = new FileWriter("src/main/resources/doctors.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(doctors, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while updating the doctors list in the JSON file.");
        }
    }

    public void staffUpdate(Staff updatedStaff, List<Staff> staffMembers) {

        int index = -1;
        for (int i = 0; i < staffMembers.size(); i++) {
            if (staffMembers.get(i).getId().equals(updatedStaff.getId())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            staffMembers.set(index, updatedStaff);
        } else {
            System.out.println("Staff member not found for updating.");
            return;
        }

        try (Writer writer = new FileWriter("src/main/resources/staff.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(staffMembers, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while updating the staff members list in the JSON file.");
        }
    }

    public boolean manageDoctor(List<Doctor> doctors) {

        try (Writer writer = new FileWriter("src/main/resources/doctors.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create a Gson instance with pretty printing
            gson.toJson(doctors, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Error while updating the doctors list in the JSON file.");
            return false;
        }
    }

    public boolean manageStaff(List<Staff> staff) {

        try (Writer writer = new FileWriter("src/main/resources/staff.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create a Gson instance with pretty printing
            gson.toJson(staff, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Error while updating the staff list in the JSON file.");
            return false;
        }
    }

    public boolean managePatient(List<Patient> patients){

        try (Writer writer = new FileWriter("src/main/resources/patients.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Create a Gson instance with pretty printing
            gson.toJson(patients, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Error while updating the patients list in the JSON file.");
            return false;
        }
    }

    public void printWelcome(){
        String filePath = "src/main/resources/welcome.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLogOut(){
        String filePath = "src/main/resources/logout.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
