package com.example.LaboBiochimie.dto;

import com.example.LaboBiochimie.Entities.AppUser;

public class UserDto {
    private String username;
    private String password;
    private String role;

    public UserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public UserDto(){super();}

    public AppUser userToAppUser(){
        AppUser appUser=new AppUser();
        appUser.setUsername(this.username);
        appUser.setPassword(this.password);
        appUser.setRole(this.role);
        return appUser;
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
}
