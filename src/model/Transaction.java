package model;

public class Transaction {
    private String id;
    private double amount;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Transaction(String id, double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public void editTransaction(double amount, String description) {
        // implementation details
    }

    public void deleteTransaction() {
        // implementation details
    }

    public String getAmount() {
        return String.valueOf(this.amount);
    }

    public String getDescription() {
        return this.description;
    }

}