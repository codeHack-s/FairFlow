package model;

import java.sql.*;
import java.util.Random;

public class IdGenerator {

    static String autoIdLengthFix(String name){
//
        int autoLength;
        if (name=="transactionId"||name=="fundId"||name=="reportId") {
            autoLength=10;
        }else{
            autoLength=6;
        }
        return generateUniqueID(autoLength);
    }
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
    public static String generateUniqueID(int length) {
        String id = generateRandomString(length);

        // Check if the ID already exists in the specified tables
        String[] tables = {"users", "transactions", "funds", "organizations"};

        try (Connection conn = Database.getConnection()) {
            for (String table : tables) {
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM " + table + " WHERE id = ?");
                stmt.setString(1, id);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                if (count > 0) {
                    // ID already exists, generate a new one
                    System.out.println("ERR DUPLICATION AUTO FIX ...");
                    return generateUniqueID(length);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // ID is unique, return it
        return id;
    }
}
