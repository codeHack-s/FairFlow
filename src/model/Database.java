package model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Database {

    //Controls Database

    private static final String URL = "jdbc:mysql://localhost:3306/fairFlow";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean registerCategory(Category category) {
        String query = "INSERT INTO categories (id, name, description) VALUES (?, ?, ?)";

        String queryForCategoryExistence = "SELECT * FROM categories WHERE name = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stz = conn.prepareStatement(queryForCategoryExistence)) {
            stz.setString(1, category.getName());
            ResultSet resultSet = stz.executeQuery();

            if (!resultSet.next()) {
                //If Category Doesn't Exist Register It
                try (PreparedStatement st = conn.prepareStatement(query)) {

                    st.setString(1, category.getId());
                    st.setString(2, category.getName());
                    st.setString(3, category.getDescription());

                    st.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }else {
                System.out.println("CATEGORY 200 OK -- Already Exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<ArrayList<String>> retrieveAllCategories() {
        String query = "SELECT * FROM categories";

        ArrayList<ArrayList<String>> categoriesList = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                ArrayList<String> category = new ArrayList<>();
                category.add( resultSet.getString("id"));
                category.add( resultSet.getString("name"));
                category.add( resultSet.getString("description"));
                categoriesList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoriesList;
    }

    public boolean registerOrganization(Organization organization) {
        String query = "INSERT INTO organizations (id, name, email, password, description, location) VALUES (?, ?, ?, ?, ?, ?)";

        String queryForCompanyExistence = "SELECT * FROM organizations WHERE name = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stz = conn.prepareStatement(queryForCompanyExistence)) {
            stz.setString(1, organization.getName());
            ResultSet resultSet = stz.executeQuery();

            if (!resultSet.next()) {
                //If Company Doesn't Exist Register It
                try (PreparedStatement st = conn.prepareStatement(query)) {

                    st.setString(1, organization.getId());
                    st.setString(2, organization.getName());
                    st.setString(3, organization.getEmail());
                    st.setString(4, organization.getPassword());
                    st.setString(5, organization.getDescription());
                    st.setString(6, organization.getDescription());

                    st.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }else {
                System.out.println("Company 200 OK -- Already Exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ArrayList<String>> getAllOrganizations() {
        String query = "SELECT * FROM organizations";
        ArrayList<ArrayList<String>> organizationsList = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(query);
             ResultSet resultSet = st.executeQuery()) {

            while (resultSet.next()) {
                ArrayList<String> organization = new ArrayList<>();
                organization.add(resultSet.getString("id"));
                organization.add(resultSet.getString("name"));
                organization.add(resultSet.getString("email"));
                organization.add(resultSet.getString("password"));
                organization.add(resultSet.getString("description"));
                organization.add(resultSet.getString("location"));

                organizationsList.add(organization);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return organizationsList;
    }

    public boolean registerFunds(Fund fund) {

        String query = "INSERT INTO funds (id, name, description, organization) VALUES (?, ?, ?, ?)";

        String queryForCompanyExistence = "SELECT * FROM organizations WHERE id = ?";
        //The organization Must be registered for them to add funds

        try (Connection conn = Database.getConnection();
             PreparedStatement stz = conn.prepareStatement(queryForCompanyExistence)) {
            stz.setString(1, fund.getOrganization());
            ResultSet resultSet = stz.executeQuery();

            if (resultSet.next()) {
                //If Company Exists Add The fund
                //--First Check if the Fund Is already added
                String queryForFundExistence = "SELECT * FROM funds WHERE name = ?";

                try (conn; PreparedStatement s = conn.prepareStatement(queryForFundExistence)) {
                    s.setString(1, fund.getName());
                    ResultSet reset = s.executeQuery();

                    if (!reset.next()) {
                        try (PreparedStatement st = conn.prepareStatement(query)) {

                            st.setString(1, fund.getId());
                            st.setString(2, fund.getName());
                            st.setString(3, fund.getDescription());
                            st.setString(4, fund.getOrganization());

                            st.executeUpdate();
                            return true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("200 OK--FUND NAME ALREADY EXISTS");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }else {
                System.out.println("Company 404 ERR -- Not Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<ArrayList<String>> getOrganizationFunds(String organizationId) {
        String query = "SELECT * FROM funds WHERE organization = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, organizationId);

            ResultSet resultSet = st.executeQuery();
            ArrayList<ArrayList<String>> funds = new ArrayList<>();

            while (resultSet.next()) {
                ArrayList<String> fund = new ArrayList<>();
                fund.add(resultSet.getString("id"));
                fund.add(resultSet.getString("name"));
                fund.add(resultSet.getString("description"));
                funds.add(fund);
            }

            if (!funds.isEmpty()) {
                return funds;
            } else {
                System.out.println("No funds found for organization with id " + organizationId);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean registerUser(User user) {
        String query = "INSERT INTO users (id, name, email, password, userType, organization) VALUES (?, ?, ?, ?, ?, ?)";

        String queryForUserExistence = "SELECT * FROM users WHERE name = ?";
        String queryForCompanyExistence = "SELECT * FROM organizations WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stz = conn.prepareStatement(queryForCompanyExistence)) {
            stz.setString(1, user.getOrganization());
            ResultSet resultSet = stz.executeQuery();

            if (!resultSet.next()) {
                //If Company Doesn't Exist
                System.out.println("Company 404");
            }else {
                try (conn;
                     PreparedStatement stmt = conn.prepareStatement(queryForUserExistence)) {
                    stmt.setString(1, user.getName());
                    ResultSet rs = stmt.executeQuery();

                    if (!rs.next()) {

                        try (PreparedStatement st = conn.prepareStatement(query)) {

                            st.setString(1, user.getId());
                            st.setString(2, user.getName());
                            st.setString(3, user.getEmail());
                            st.setString(4, user.getPassword());
                            st.setString(5, user.getAccountType());
                            st.setString(6, user.getOrganization());

                            st.executeUpdate();
                            return true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("User name " + user.getName() + " is already registered");
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User authenticateUser(String username, String password) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String accountType = rs.getString("userType");
                String email = rs.getString("email");
                String organization = rs.getString("organization");
                return new User(id, username, accountType, password, email, organization);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean registerTransaction(Transaction transaction, String fundId, String categoryId, String userId) {
        String query = "INSERT INTO transactions (id, amount, description, fund, category, user) VALUES (?, ?, ?, ?, ?, ?)";

        String queryForTransactionExistence = "SELECT * FROM transactions WHERE description = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stz = conn.prepareStatement(queryForTransactionExistence)) {
            stz.setString(1, transaction.getDescription());
            ResultSet resultSet = stz.executeQuery();

            if (!resultSet.next()) {
                //If Transaction Doesn't Exist Register It
                try (PreparedStatement st = conn.prepareStatement(query)) {

                    st.setString(1, transaction.getId());
                    st.setString(2, transaction.getAmount());
                    st.setString(3, transaction.getDescription());
                    st.setString(4, fundId);
                    st.setString(5, categoryId);
                    st.setString(6, userId);

                    st.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }else {
                System.out.println("Transaction 200 OK -- Already Exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ArrayList<String>> getAllTransactions() {
    String query = "SELECT * FROM transactions";
    ArrayList<ArrayList<String>> transactions = new ArrayList<>();

    try (Connection conn = Database.getConnection();
         PreparedStatement st = conn.prepareStatement(query)) {

        ResultSet resultSet = st.executeQuery();

        while (resultSet.next()) {
            ArrayList<String> transaction = new ArrayList<>();
            transaction.add(resultSet.getString("id"));
            transaction.add(resultSet.getString("amount"));
            transaction.add(resultSet.getString("description"));
            transaction.add(resultSet.getString("fund"));
            transaction.add(resultSet.getString("category"));
            transaction.add(resultSet.getString("user"));
            transactions.add(transaction);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return transactions;
}

    public ArrayList<ArrayList<String>> getTransactionsForFund(String fundId) {
        String query = "SELECT transactions.id, transactions.amount, transactions.description, categories.name, users.name " +
                "FROM transactions " +
                "INNER JOIN categories ON transactions.category = categories.id " +
                "INNER JOIN users ON transactions.user = users.id " +
                "WHERE transactions.fund = ?";

        ArrayList<ArrayList<String>> transactions = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, fundId);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                ArrayList<String> transactionData = new ArrayList<>();
                transactionData.add(resultSet.getString(1)); // Transaction ID
                transactionData.add(resultSet.getString(2)); // Transaction Amount
                transactionData.add(resultSet.getString(3)); // Transaction Description
                transactionData.add(resultSet.getString(4)); // Category Name
                transactionData.add(resultSet.getString(5)); // User Username

                transactions.add(transactionData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public boolean registerCorruptionCase(String reportId, String reportName, String description,String transactionId, String userId) {
    String query = "INSERT INTO reports (id, report, description, transaction, userId) VALUES (?, ?, ?, ?, ?)";

    String queryForUserExistence = "SELECT * FROM users WHERE id = ?";
    String queryForTransactionExistence = "SELECT * FROM transactions WHERE id = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement stz1 = conn.prepareStatement(queryForUserExistence);
         PreparedStatement stz2 = conn.prepareStatement(queryForTransactionExistence)) {

        // Check if user exists
        stz1.setString(1, userId);
        ResultSet userResultSet = stz1.executeQuery();

        // Check if transaction exists
        stz2.setString(1, transactionId);
        ResultSet transactionResultSet = stz2.executeQuery();

        if (userResultSet.next() && transactionResultSet.next()) {
            // If user and transaction exist, register the case
            try (PreparedStatement st = conn.prepareStatement(query)) {
                st.setString(1, reportId);
                st.setString(2, reportName);
                st.setString(3, description);
                st.setString(4, transactionId);
                st.setString(5, userId);

                st.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "USER ERR 404 :: NOT FOUND.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

}
