
// program for Transaction Handling...

import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded succesfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection established succesfully");
            con.setAutoCommit(false);
            try {

                PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = con.prepareStatement(depositQuery);
                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account123");
                depositStatement.setDouble(1, 500);
                depositStatement.setString(2, "account456");
                int rowsAfftectedWithdrawl = withdrawStatement.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();
                if (rowsAfftectedWithdrawl>0 && rowsAffectedDeposit>0) {
                    con.commit();
                    System.out.println("Transaction succesfull");
                }
                else {
                    con.rollback();
                    System.out.println("Transaction failed");

                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }


        }catch (SQLException e) {
            System.out.println(e.getMessage());


        }
    }
}