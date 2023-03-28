package model;



import java.util.ArrayList;
import java.util.List;

public class Operations {

    public ArrayList<User> currentUser = new ArrayList<>();
    // method to register a new user
    public boolean registerUser(String username,String accountType, String password, String email,String organization) {
        // implementation
        User user = new User(IdGenerator.autoIdLengthFix("userId"),username,accountType,password,email,organization);
        return new Database().registerUser(user);
    }

    // method to authenticate a user
    public User authenticateUser(String username, String password) {
        // implementation
        User thisUser = new Database().authenticateUser(username,password);
        currentUser.add(0,thisUser);
        return thisUser;
    }

    // method to create a new organization will return true if creation success
    public boolean createOrganization(String name, String email, String password, String description, String location) {
        // implementation
        return new Database().registerOrganization(new Organization(IdGenerator.autoIdLengthFix("organizationId"), name, email, password, description, location));
    }

    // method to retrieve a list of all organizations
    public ArrayList<ArrayList<String>> getAllOrganizations() {
        // implementation
        if(!hasData(new Database().getAllOrganizations())){
            System.out.println("DATA RETRIEVAL 404 :: NO DATA RETRIEVED");
            return null;
        }else {
            System.out.println("DATA RETRIEVAL SUCCESS :: 200 OK");
            return new Database().getAllOrganizations();
        }
    }
    
    //
    
    public ArrayList<ArrayList<String>> getAllTransactions() {
        // implementation
        if(!hasData(new Database().getAllTransactions())){
            System.out.println("DATA RETRIEVAL 404 :: NO DATA RETRIEVED");
            return null;
        }else {
            System.out.println("DATA RETRIEVAL SUCCESS :: 200 OK");
            return new Database().getAllTransactions();
        }
    }

    // method to create a new fund for an organization
    public boolean createFund(String name, String description, String organizationId) {
        // implementation
        return new Database().registerFunds(new Fund(IdGenerator.autoIdLengthFix("fundId"),name, description, organizationId));
    }

    // method to retrieve a list of all funds for an organization
    public ArrayList<ArrayList<String>> getFundsForOrganization(String organizationId) {
        // implementation
        if(!hasData(new Database().getOrganizationFunds(organizationId))){
            System.out.println("Data Unavailable");
            return null;
        }else {
            System.out.println("Data Detected");
            return new Database().getOrganizationFunds(organizationId);
        }
    }

    // method to create a new transaction for a fund
    public boolean createTransaction(int amount, String description, String fundId, String categoryId, String userId) {
        // implementation
        return new Database().registerTransaction(new Transaction( IdGenerator.autoIdLengthFix("organizationId"), amount,description),fundId,categoryId,userId);
    }

    // method to retrieve a list of all transactions for a fund
    public ArrayList<ArrayList<String>> getTransactionsForFund(String fundId) {
        // implementation
        return new Database().getTransactionsForFund(fundId);
    }

    // method to create a new category
    public boolean createCategory(String name, String description) {
        // implementation
        return new Database().registerCategory(new Category(IdGenerator.autoIdLengthFix("categoryId"),name,description));
    }

    // method to retrieve a list of all categories
    public ArrayList<ArrayList<String>> getAllCategories() {
        // implementation
        if(!hasData(new Database().retrieveAllCategories())){
            System.out.println("Data Unavailable");
            return null;
        }else {
            System.out.println("Data Detected");
            return new Database().retrieveAllCategories();
        }
    }

    // method to assign a role to a user
    public void assignUserRole(int userId, int userRoleId) {
        // implementation
    }

    // method to retrieve a list of all user roles
    public List<String> getAllUserRoles() {
        // implementation
        return null;
    }

    // method to detect potential corruption based on transaction patterns
    public List<Transaction> detectPotentialCorruption(int fundId) {
        // implementation
        return null;
    }

    // method to allow users to report corruption
    public boolean reportCorruption(String caseName, String transactionId, String description, String userId) {
        // implementation
        return new Database().registerCorruptionCase(IdGenerator.autoIdLengthFix("reportId"),caseName,description,transactionId,userId);
    }

    public ArrayList<ArrayList<String>> getAllCorruptionCases(){
        return new Database().getAllCorruptionCases();
    }
    
    // method to allow managers to approve or reject transactions
    public void approveTransaction(int transactionId, boolean approved) {
        // Implementation
        // If it is in the budget

    }

    // method to flag transactions as potential cash misuse
    public void flagCashMisuse(int transactionId, String description) {
        // implementation
    }

    //Method to check if data exists in any arraylist
    public boolean hasData(ArrayList<ArrayList<String>> arrayList) {
        return !arrayList.isEmpty();
    }


}
