package model;

import java.util.ArrayList;

public class Fund {
    private String name;
    private String id;
    private String organization;
    private String description;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Fund(String id, String name, String description, String organization) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.transactions = new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction transaction) {
        // implementation details
    }

    public void deleteTransaction(Transaction transaction) {
        // implementation details
    }

    public void transferFunds(double amount, Fund destination) {
        // implementation details
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void deposit(double amount) {
        // implementation details
    }

    public void withdraw(double amount) {
        // implementation details
    }
}