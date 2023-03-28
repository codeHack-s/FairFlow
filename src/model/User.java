package model;

public class User {
    private String username;
    private String id;
    private String password;
    private String email;
    private String accountType;
    private String organization;

    public User(String id, String username,String accountType, String password, String email,String organization) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.email = email;
        this.id = id;
        this.organization = organization;
    }

    public void createUser() {
        // implementation details
    }

    public void login() {
        // implementation details
    }

    public void updateProfile() {
        // implementation details
    }

    public void deleteAccount() {
        // implementation details
    }

    public String getEmail() {
        return email;
    }


    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getOrganization() {
        return organization;
    }
}