package model;

import model.Operations;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //The list controls the state of the application
    static List<ArrayList<String>> currentUserMain = new ArrayList<>();
    public static void main(String[] args){

        //? new Database().registerOrganization(new Organization("S2RCX","kenTom","kentom@fairFlow.com","kenTom","A kenTom company","NAIROBI"));

        //?new Database().registerFunds(new Fund(IdGenerator.autoIdLengthFix("fundId"),"Chuka Uni Fund","Funds To Test Db","RACSAM"));
        //new Database().registerCategory(new Category(IdGenerator.autoIdLengthFix("categoryId"),"OFFLINE FUNDS","FUNDS OFFERED IN CASH"));

        Operations operations = new Operations();
        Database db = new Database();


        //System.out.println(db.registerCorruptionCase(IdGenerator.autoIdLengthFix("reportId"),"Test From Ide","Chuka Uni Fund Issues","H8R3DX9CGQ","S2RCX"));


        //System.out.println(db.retrieveAllCategories());

        //System.out.println(operations.getTransactionsForFund("ABCDE12345"));

        //System.out.println(db.getOrganizationFunds("RACSAM"));

        //?System.out.println(new IdGenerator().autoIdLengthFix("transactionId")+"HH");
        //?String transactionId = new IdGenerator().autoIdLengthFix("transactionId");
        //?addUserToList("Steve","admin");
        //?new Database().registerTransaction(new Transaction( transactionId, 1500,"SENT TO COVER FUNDS"),"ABCDE12345","123SDE","S2RCX");
        //?System.out.println(operations.registerUser("S2RCX","kenTom","kentom@fairFlow.com","kenTom","A kenTom company","RACSAM"));

       //?System.out.println(new Database().getAllOrganizations());
        //System.out.println(operations.getAllOrganizations());

        /*?
        for (ArrayList<String> row: operations.getAllOrganizations()) {
            System.out.println(row);
        }
        */


        /*
        Initialize the FairFlowController: The main class first creates an instance of the FairFlowController, which is the class responsible for managing the different operations and functionality of the application.

        Get a list of all transactions for a particular fund: The main class calls a method on the FairFlowController to retrieve all transactions associated with a particular fund. This list of transactions will be used for subsequent operations.

        Detect potential corruption in the fund's transactions: The FairFlowController is used to detect any potential instances of corruption in the transactions associated with the fund. This could involve analyzing transaction patterns, checking for unusual activity, or applying other rules and heuristics to identify suspicious behavior.

        Report potential corruption to system administrators: If potential corruption is detected, the main class calls a method on the FairFlowController to report the suspicious activity to system administrators or other authorized personnel. This could involve sending a notification or alert, generating a report, or taking other actions to ensure that the issue is addressed appropriately.

        Approve or reject transactions: The main class uses the FairFlowController to evaluate each transaction in the list and determine whether it should be approved or rejected based on various factors such as the amount, category, or other criteria. This might involve applying business rules or policies to make decisions and flagging transactions that require additional review or investigation.

        Flag transactions as potential cash misuse: Finally, the FairFlowController can be used to flag transactions that may be indicative of cash misuse, such as transactions involving large amounts of cash or those that violate other policies or regulations. These flagged transactions can be reviewed further or escalated as needed to address any potential issues.

      */
    }

    //Add The User To A Local List
    private static void addUserToList(String username, String password){
        ArrayList<String> currentUserData = new ArrayList<>();
        User currentUser = new Operations().authenticateUser(username,password);
        currentUserData.add(currentUser.getId());
        currentUserData.add(currentUser.getName());
        currentUserData.add(currentUser.getEmail());
        currentUserData.add(currentUser.getAccountType());
        currentUserData.add(currentUser.getOrganization());
        currentUserData.add(currentUser.getPassword());
        currentUserMain.add(currentUserData);
    }

}
