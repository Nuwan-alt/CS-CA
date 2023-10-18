package org.example;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SystemHandler {
    private Integer userId;
    private String role;
    private String userPassword;
    private String username;
    private List<User> users;
    private List<Patient> patients;
    private PasswordUtils passwordUtils;
    List<User> userList;

    public SystemHandler(  String username,String userPassword, List<User> users, List<Patient> patients) {
        this.userPassword = userPassword;
        this.username = username.toLowerCase().trim();
        this.users = users;
        this.patients = patients;
         this.userList = Stream.concat(users.stream(), patients.stream())
                .toList();
        this.passwordUtils = new PasswordUtils();
    }
    public void runSystem(){
        boolean isLogged = this.login();
        if(isLogged) {
            System.out.println("logged");
        }
    }
    private boolean login(){
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
            }

        }else {
            System.out.println("Invalid credentials");
        }
        return isLogged;
    }

}
