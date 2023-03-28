package model;

import java.util.ArrayList;

public class Organization {
    private String email;
    private String password;
    private String id;
    private String name;
    private String description;
    private String location;
    private ArrayList<User> members;
    private ArrayList<Fund> funds;

    public Organization(String id, String name, String email, String password, String description, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.location = location;
        this.members = new ArrayList<User>();
        this.funds = new ArrayList<Fund>();
    }

    public void createOrganization() {
        // implementation details
    }

    public void addMember(User member) {
        // implementation details
    }

    public void removeMember(User member) {
        // implementation details
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
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void createFund(Fund fund) {
        // implementation details
    }

    public void deleteFund(Fund fund) {
        // implementation details
    }
}