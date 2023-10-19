package org.example;

import java.util.List;

public class User{
    private Integer id;
    private String username;
    private String password;
    private String role;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }
    void viewAllDoctors(List<Doctor> doctors){
        String formattedLine1 = String.format("%-10s %-15s: %s", "ID", "Doctor Name", "Ward");
        System.out.println(formattedLine1);
        for (Doctor doctor : doctors) {
            String formattedOutput = String.format("%-10s %-15s: %s",doctor.getId(), doctor.getUsername(), doctor.getWard());
            System.out.println(formattedOutput);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(Integer id, String username, String role,Integer age) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.age = age;
    }


}
