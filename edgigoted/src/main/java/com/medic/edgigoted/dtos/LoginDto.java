package com.medic.edgigoted.dtos;

public class LoginDto {
    private String email;
    private String password;

    public LoginDto() {

    }
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}