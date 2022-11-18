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
        System.out.println("4. Show content of a table");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }

    public static void createTables(Statement stmt) {
        try {
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
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        System.out.println("Processing...Done! Database is initialized!");
        System.out.println();
    }

    public static void deleteTables(Statement stmt) {
        try {
            stmt.executeUpdate("DROP TABLE IF EXISTS Category");
            stmt.executeUpdate("DROP TABLE IF EXISTS Manufacturer");
            stmt.executeUpdate("DROP TABLE IF EXISTS Part");
            stmt.executeUpdate("DROP TABLE IF EXISTS Salesperson");
            stmt.executeUpdate("DROP TABLE IF EXISTS Transaction");
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        System.out.println("Processing...Done! Database is removed!");
        System.out.println();
    }

    public static void loadDataFile(Connection conn, String dataFolderPath) {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(new File(dataFolderPath + "/category.txt")));
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Category VALUES (?, ?)");
            String thisLine;
            while ((thisLine = fin.readLine()) != null) {
                String[] data = thisLine.split("\t");
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setString(2, data[1]);
                pstmt.executeUpdate();
            }
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        try {
            BufferedReader fin = new BufferedReader(new FileReader(new File(dataFolderPath + "/manufacturer.txt")));
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Manufacturer VALUES (?, ?, ?, ?)");
            String thisLine;
            while ((thisLine = fin.readLine()) != null) {
                String[] data = thisLine.split("\t");
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setString(2, data[1]);
                pstmt.setString(3, data[2]);
                pstmt.setInt(4, Integer.parseInt(data[3]));
                pstmt.executeUpdate();
            }
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        try {
            BufferedReader fin = new BufferedReader(new FileReader(new File(dataFolderPath + "/part.txt")));
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Part VALUES (?, ?, ?, ?, ?, ?, ?)");
            String thisLine;
            while ((thisLine = fin.readLine()) != null) {
                String[] data = thisLine.split("\t");
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setString(2, data[1]);
                pstmt.setInt(3, Integer.parseInt(data[2]));
                pstmt.setInt(4, Integer.parseInt(data[3]));
                pstmt.setInt(5, Integer.parseInt(data[4]));
                pstmt.setInt(6, Integer.parseInt(data[5]));
                pstmt.setInt(7, Integer.parseInt(data[6]));
                pstmt.executeUpdate();
            }
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        try {
            BufferedReader fin = new BufferedReader(new FileReader(new File(dataFolderPath + "/salesperson.txt")));
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Salesperson VALUES (?, ?, ?, ?, ?)");
            String thisLine;
            while ((thisLine = fin.readLine()) != null) {
                String[] data = thisLine.split("\t");
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setString(2, data[1]);
                pstmt.setString(3, data[2]);
                pstmt.setInt(4, Integer.parseInt(data[3]));
                pstmt.setInt(5, Integer.parseInt(data[4]));
                pstmt.executeUpdate();
            }
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        try {
            BufferedReader fin = new BufferedReader(new FileReader(new File(dataFolderPath + "/transaction.txt")));
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Transaction VALUES (?, ?, ?, ?)");
            String thisLine;
            while ((thisLine = fin.readLine()) != null) {
                String[] data = thisLine.split("\t");
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setString(2, data[1]);
                pstmt.setString(3, data[2]);
                String[] dateArray = data[3].split("/");
                String date = dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];
                pstmt.setDate(4, java.sql.Date.valueOf(date));
                pstmt.executeUpdate();
            }
        } catch(Exception e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }

        System.out.println("Processing...Done! Data is inputted to the database!");
        System.out.println();
    }

    public static void printTable(Statement stmt, String table) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            System.out.print("|");
            for (int i = 1; i <= columnsNumber; i++)
                System.out.print(" " + rsmd.getColumnName(i) + " " + "|");
            System.out.println();

            while (rs.next()) {
		        System.out.print("|");
                for (int i = 1; i <= columnsNumber; i++)
                    System.out.print(" " + rs.getString(i) + " " + "|");
                System.out.println();
            }
            System.out.println();
        } catch(SQLException e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }
    }
    // End of administrator functions

    public static void printSalespersonMenu() {
        System.out.println();
        System.out.println("-----Operations for salesperson menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Search for parts");
        System.out.println("2. Sell a part");
        System.out.println("3. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }

    public static void searchPart(Statement stmt, String searchKeyword, int order) {
        try {
            if (order == 1) {
                ResultSet rs = stmt.executeQuery("SELECT pID, pName, mName, cName, pAvailableQuantity" + 
                ", pWarrantyPeriod, pPrice FROM Part P, Manufacturer M, Category C" + 
                " WHERE P.mID = M.mID AND P.cID = C.cID AND P.pName LIKE '%" + searchKeyword + "%'" + 
                " ORDER BY P.pPrice ASC");
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
                while (rs.next()) {
                    System.out.print("|");
                    for (int i = 1; i <= columnsNumber; i++)
                        System.out.print(" " + rs.getString(i) + " " + "|");
                    System.out.println();
                }

                System.out.println("End of Query");
                System.out.println();
            }

            else if (order == 2) {
                ResultSet rs = stmt.executeQuery("SELECT pID, pName, mName, cName, pAvailableQuantity" + 
                ", pWarrantyPeriod, pPrice FROM Part P, Manufacturer M, Category C" + 
                " WHERE P.mID = M.mID AND P.cID = C.cID AND P.pName LIKE '%" + searchKeyword + "%'" + 
                " ORDER BY P.pPrice DESC");
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
                while (rs.next()) {
                    System.out.print("|");
                    for (int i = 1; i <= columnsNumber; i++)
                        System.out.print(" " + rs.getString(i) + " " + "|");
                    System.out.println();
                }

                System.out.println("End of Query");
                System.out.println();
            }

            else {
                System.out.println("Invalid input. Please try again.");
                System.out.println();
            }
            
        } catch(SQLException e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }
    }

    public static void searchManufacturer(Statement stmt, String searchKeyword, int order) {
        try {
            if (order == 1) {
                ResultSet rs = stmt.executeQuery("SELECT pID, pName, mName, cName, pAvailableQuantity" + 
                ", pWarrantyPeriod, pPrice FROM Part P, Manufacturer M, Category C" + 
                " WHERE P.mID = M.mID AND P.cID = C.cID AND M.mName LIKE '%" + searchKeyword + "%'" + 
                " ORDER BY P.pPrice ASC");
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
                while (rs.next()) {
                    System.out.print("|");
                    for (int i = 1; i <= columnsNumber; i++)
                        System.out.print(" " + rs.getString(i) + " " + "|");
                    System.out.println();
                }

                System.out.println("End of Query");
                System.out.println();
            }

            else if (order == 2) {
                ResultSet rs = stmt.executeQuery("SELECT pID, pName, mName, cName, pAvailableQuantity" + 
                ", pWarrantyPeriod, pPrice FROM Part P, Manufacturer M, Category C" + 
                " WHERE P.mID = M.mID AND P.cID = C.cID AND M.mName LIKE '%" + searchKeyword + "%'" + 
                " ORDER BY P.pPrice DESC");
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
                while (rs.next()) {
                    System.out.print("|");
                    for (int i = 1; i <= columnsNumber; i++)
                        System.out.print(" " + rs.getString(i) + " " + "|");
                    System.out.println();
                }

                System.out.println("End of Query");
                System.out.println();
            }

            else {
                System.out.println("Invalid input. Please try again.");
                System.out.println();
            }
            
        } catch(SQLException e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }
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
                 else if (menuChoice == 1) {
                    printAdministratorMenu();
                    int administratorChoice;
                    administratorChoice = Integer.parseInt(in.readLine());
                    if (administratorChoice == 5) continue;

                    else if (administratorChoice == 1) {
                        createTables(stmt);
                    }

                    else if (administratorChoice == 2) {
                        deleteTables(stmt);
                    }

                    else if (administratorChoice == 3) {
                        System.out.print("Type in the Source Data Folder Path: ");
                        String dataFolderPath;
                        dataFolderPath = in.readLine();
                        loadDataFile(conn, dataFolderPath);
                    }

                    else if (administratorChoice == 4) {
                        System.out.print("Which table would you like to show: ");
                        String table;
                        table = in.readLine();
                        printTable(stmt, table);
                    }
                    
                    else {
                        System.out.println("Invalid input. Please try again.");
                        System.out.println();
                    }
                }

                // Salesperson Operations
                else if (menuChoice == 2) {
                    printSalespersonMenu();
                    int salespersonChoice;
                    salespersonChoice = Integer.parseInt(in.readLine());
                    if (salespersonChoice == 3) continue;

                    else if (salespersonChoice == 1) {
                        System.out.println("Choose the Search criterion: ");
                        System.out.println("1. Part Name");
                        System.out.println("2. Manufacturer Name");
                        System.out.print("Choose the search criterion: ");
                        int searchCriterion;
                        searchCriterion = Integer.parseInt(in.readLine());
                        System.out.print("Type in the Search Keyword: ");
                        String searchKeyword;
                        searchKeyword = in.readLine();
                        System.out.println("Choose ordering: ");
                        System.out.println("1. By price, ascending order");
                        System.out.println("2. By price, descending order");
                        System.out.print("Choose the search criterion: ");
                        int order;
                        order = Integer.parseInt(in.readLine());

                        if (searchCriterion == 1) {
                            searchPart(stmt, searchKeyword, order);
                        }

                        else if (searchCriterion == 2) {
                            searchManufacturer(stmt, searchKeyword, order);
                        }
                        
                        else {
                            System.out.println("Invalid input. Please try again.");
                            System.out.println();
                        }
                    }
                    
                    else if (salespersonChoice == 2) {
                        
                    }

                    else {
                        System.out.println("Invalid input. Please try again.");
                        System.out.println();
                    }
                }
                
                else if (menuChoice == 3) {
                    
                }

                else {
                    System.out.println("Invalid input. Please try again.");
                    System.out.println();
                }
            }
        } catch(Exception x) {
            System.err.println("Unable to load the driver class!");
        }
    }
}