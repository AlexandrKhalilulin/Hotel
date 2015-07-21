package com.epam.ak.model;

import java.util.UUID;

public abstract class User extends BaseEntity {
    private String login;
    private String password;
    private String lastName;
    private String surName;
    private String contactPhone;
    private String email;
    private String sex;

    public User() {
    }

    public User(UUID uuid, int id, String sex, String email, String contactPhone, String surName, String lastName, String password, String login) {
        super(uuid, id);
        this.sex = sex;
        this.email = email;
        this.contactPhone = contactPhone;
        this.surName = surName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public enum Sex {MALE, FEMALE}

}
