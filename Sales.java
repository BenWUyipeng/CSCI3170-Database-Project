import java.sql.*;
import java.io.*;
        
public class Sales {

    public static void printMenu() {
        System.out.println("Welcome to sales system!");
        System.out.println();
        System.out.println("-----Main menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Operations for administrator");
        System.out.println("2. Operations for salesperson");
        System.out.println("3. Operations for manager");
        System.out.println("4. Exit this program");
        System.out.print("Enter Your Choice: ");
    }

    public static void printAdministratorMenu() {
        System.out.println();
        System.out.println("-----Operations for administrator menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }

    public static void createTables(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE Category("
        + "cID INT(1) NOT NULL, "
        + "cName VARCHAR (20) NOT NULL, "
        + "PRIMARY KEY (cID))");

        stmt.executeUpdate("CREATE TABLE Manufacturer("
        + "mID INT(2) NOT NULL, "
        + "mName VARCHAR (20) NOT NULL, "
        + "mAddress VARCHAR (50) NOT NULL, "
        + "mPhoneNumber INT(8) NOT NULL, "
        + "PRIMARY KEY (mID))");

        stmt.executeUpdate("CREATE TABLE Part("
        + "pID INT(3) NOT NULL, "
        + "pName VARCHAR (20) NOT NULL, "
        + "pPrice INT(5) NOT NULL, "
        + "mID INT(2) NOT NULL, "
        + "cID INT(1) NOT NULL, "
        + "pWarrantyPeriod INT(2) NOT NULL, "
        + "pAvailableQuantity INT(2) NOT NULL, "
        + "PRIMARY KEY (pID))");

        stmt.executeUpdate("CREATE TABLE Salesperson("
        + "sID INT(2) NOT NULL, "
        + "sName VARCHAR (20) NOT NULL, "
        + "sAddress VARCHAR (50) NOT NULL, "
        + "sPhoneNumber INT(8) NOT NULL, "
        + "sExperience INT(1) NOT NULL, "
        + "PRIMARY KEY (sID))");

        stmt.executeUpdate("CREATE TABLE Transaction("
        + "tID INT(4) NOT NULL, "
        + "pID INT(3) NOT NULL, "
        + "sID INT(2) NOT NULL, "
        + "tDate DATE NOT NULL, "
        + "PRIMARY KEY (tID))");
    }

    public static void main(String[] args) throws Exception {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
        "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db17", "Group17", "CSCI3170");

            Statement stmt = conn.createStatement();

            int menuChoice;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                printMenu();
                menuChoice = Integer.parseInt(in.readLine());
                if (menuChoice == 4) break;

                // Administrator Operations
                if (menuChoice == 1) {
                    printAdministratorMenu();
                    int administratorChoice;
                    administratorChoice = Integer.parseInt(in.readLine());
                    if (administratorChoice == 5) continue;

                    else if (administratorChoice == 1) {
                        createTables(stmt);
                    }
                }
            }
            

        } catch(Exception x) {
            System.err.println("Unable to load the driver class!");
        }



            
    }
}
