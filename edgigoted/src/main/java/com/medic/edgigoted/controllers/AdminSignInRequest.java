package com.medic.edgigoted.controllers;

public class AdminSignInRequest {

    private String email; // Changed from username to email
    private String password;

    // Constructors, getters, and setters

    public AdminSignInRequest() {
    }

    public AdminSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
