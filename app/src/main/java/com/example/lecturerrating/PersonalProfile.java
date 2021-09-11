package com.example.lecturerrating;

public class PersonalProfile {

    private String username;
    private String email;
    private String password;
    private String photo_path;

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    @Override
    public String toString() {
        return "PersonalProfile{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public PersonalProfile(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        photo_path = "empty";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
        password = password;
    }
}

