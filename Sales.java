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

            System.out.println("Content of table " + table + ":");
            System.out.print("|");
            for (int i = 1; i <= columnsNumber; i++)
                System.out.print(" " + rsmd.getColumnName(i) + " " + "|");
            System.out.println();

            if (table.equals("Transaction") == false) {
                while (rs.next()) {
                    System.out.print("|");
                    for (int i = 1; i <= columnsNumber; i++)
                        System.out.print(" " + rs.getString(i) + " " + "|");
                    System.out.println();
                }
            } else {
                while (rs.next()) {
                    System.out.print("|");
                    for (int i = 1; i <= columnsNumber-1; i++)
                        System.out.print(" " + rs.getString(i) + " " + "|");
                    String[] date = rs.getString(columnsNumber).split("-");
                    System.out.print(" " + date[2] + "/" + date[1] + "/" + date[0]);
                    System.out.println();
                }
            }

            System.out.println();
        } catch(SQLException e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }
    }

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
            if (order != 1 && order != 2) {
                System.out.println("Invalid input. Please try again.");
                System.out.println();
                return;
            } else {
                String order_str = order == 1 ? "ASC" : "DESC";
                ResultSet rs = stmt.executeQuery("SELECT pID, pName, mName, cName, pAvailableQuantity" + 
                ", pWarrantyPeriod, pPrice FROM Part P, Manufacturer M, Category C" + 
                " WHERE P.mID = M.mID AND P.cID = C.cID AND P.pName LIKE '%" + searchKeyword + "%'" + 
                " ORDER BY P.pPrice " + order_str);
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
        } catch(SQLException e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }
    }

    public static void searchManufacturer(Statement stmt, String searchKeyword, int order) {
        try {
            if (order != 1 && order != 2) {
                System.out.println("Invalid input. Please try again.");
                System.out.println();
                return;
            } else {
                String order_str = order == 1 ? "ASC" : "DESC";
                ResultSet rs = stmt.executeQuery("SELECT pID, pName, mName, cName, pAvailableQuantity" + 
                ", pWarrantyPeriod, pPrice FROM Part P, Manufacturer M, Category C" + 
                " WHERE P.mID = M.mID AND P.cID = C.cID AND M.mName LIKE '%" + searchKeyword + "%'" + 
                " ORDER BY P.pPrice " + order_str);
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
        } catch(SQLException e) {
            System.err.println("Error: " + e);
            System.out.println();
            return;
        }
    }
    
    public static void performTransaction(Statement stmt, int partID, int salespersonID)
    {
      
        try{
            boolean partAvailable = false;
            ResultSet rs = stmt.executeQuery("SELECT mID, pName, pAvailableQuantity FROM Part WHERE pID=" + partID);
            rs.next();
            String mID = rs.getString(1);
            String pName = rs.getString(2);
            String quantityString = rs.getString(3);
            int quantity = Integer.parseInt(quantityString);
            if (quantity>0)
            {
                partAvailable = true;
            }
            if (partAvailable)
            {
                ResultSet rs2 = stmt.executeQuery("SELECT mName FROM Manufacturer "
                + "WHERE mID="+mID);
                rs2.next();
                quantity = quantity-1;
                stmt.executeUpdate("UPDATE Part SET pAvailableQuantity =" + quantity + " WHERE pID ="+ partID);
                System.out.println("Product: " + pName + "(id: " + partID + ") Remaining Quantity: " + quantity);
                System.out.println();
                
                //Getting the system date of the MySQL DBMS server
                ResultSet rs3 = stmt.executeQuery("SELECT CURDATE()");
                rs3.next();
                String date = rs3.getString(1);
                
                
                //check if any tuples in transaction
                rs = stmt.executeQuery("SELECT count(*) FROM Transaction");
                rs.next();
                String recordNum_Str = rs.getString(1);
                int recordNum_Int = Integer.parseInt(recordNum_Str);
                int transactionID;
                if (recordNum_Int>0)
                {
                    rs = stmt.executeQuery("SELECT MAX(tID) FROM Transaction");
                    rs.next();
                    String transactionID_string = rs.getString(1);
                    transactionID = Integer.parseInt(transactionID_string);
                    transactionID = transactionID + 1;
                }else
                {
                    transactionID = 1;
                }
                stmt.executeUpdate("INSERT INTO Transaction(tID,pID,sID,tDate) VALUES(" + transactionID + "," 
                + partID + "," + salespersonID + "," + "'" + date + "'" + ")");
                
            }else
            {
                System.out.println("Error Message: Not available! The part cannot be sold!");
                System.out.println();
            }
            
        }catch(Exception e){
            System.err.println("Error: " + e);
            System.out.println();
        }
        
    }
    
    public static void printManagerMenu()
    {
        System.out.println();
        System.out.println("-----Operations for manager menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. List all salespersons");
        System.out.println("2. Count the no. of sales record of each salesperson under a specific range on years of "
        + "experience");
        System.out.println("3. Show the total sales value of each manufacturer");
        System.out.println("4. Show the N most popular part");
        System.out.println("5. Return to the main menu");
    }
    
    public static void listAllSalespersons(Statement stmt, int order)
    {
        try
        {
            System.out.println("| ID | Name | Mobile Phone | Years of Experience |");
            //order=1 , ascending
            if (order==1)
            {
                ResultSet rs = stmt.executeQuery("SELECT sID, sName, sPhoneNumber, sExperience FROM Salesperson "
                + "ORDER BY sExperience ASC");
                while (rs.next())
                {
                    String sID = rs.getString(1);
                    String sName = rs.getString(2);
                    String sPhoneNumber = rs.getString(3);
                    String sExperience = rs.getString(4);
                    System.out.println("| "+sID+" | "+sName+" | "+sPhoneNumber+" | "+sExperience+" |");
                }
                System.out.println();
            }
            //order=2 descending
            if (order==2)
            {
                ResultSet rs = stmt.executeQuery("SELECT sID, sName, sPhoneNumber, sExperience FROM Salesperson "
                + "ORDER BY sExperience DESC");
                while (rs.next())
                {
                    String sID = rs.getString(1);
                    String sName = rs.getString(2);
                    String sPhoneNumber = rs.getString(3);
                    String sExperience = rs.getString(4);
                    System.out.println("| "+sID+" | "+sName+" | "+sPhoneNumber+" | "+sExperience+" |");
                }
                System.out.println();
            }
        }catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.out.println();
        }           
    }
    
    public static void countTransaction(Statement stmt, String lowerBound, String upperBound, Connection conn)
    {
        try
        {
            System.out.println("Transaction Record:");
            System.out.println("| ID | Name | Years of Experience | Number of Transaction |");
            ResultSet rs = stmt.executeQuery("SELECT sID, sName, sExperience FROM Salesperson "
            + "WHERE sExperience>="+lowerBound+" AND sExperience<="+upperBound+" ORDER BY sID DESC");
            Statement stmt2 = conn.createStatement();
            while (rs.next())
            {
                String sID = rs.getString(1);
                String sName = rs.getString(2);
                String sExperience = rs.getString(3);
                ResultSet rs2 = stmt2.executeQuery("SELECT count(*) FROM Transaction "
                + "WHERE sID="+sID);
                rs2.next();
                String transactionNum = rs2.getString(1);
                System.out.println("| "+sID+" | "+sName+" | "+sExperience+" | "+transactionNum+" |");
                
            }
            System.out.println("End of Query");
            System.out.println();
        }catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.out.println();
        }
    }
    
    public static void showTotalRevenue(Statement stmt, Connection conn)
    {
        try
        {
            System.out.println("| Manufacturer ID | Manufacturer Name | Total Sales Value |");
            stmt.executeUpdate("CREATE TEMPORARY TABLE temp_manufacturer SELECT * FROM Manufacturer");
            //stmt.executeQuery("SELECT * INTO temp_manufacturer FROM Manufacturer");
            stmt.executeUpdate("ALTER TABLE temp_manufacturer ADD totalRevenue INT DEFAULT 0");
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT pID, mID, pPrice FROM Part");
            while (rs.next())
            {
                String pID = rs.getString(1);
                String mID = rs.getString(2);
                String pPrice = rs.getString(3);
                ResultSet rs2 = stmt2.executeQuery("SELECT count(*) FROM Transaction WHERE "
                + "pID="+pID);
                int soldQuantity;
                if (rs2.next())
                {
                    soldQuantity = Integer.parseInt(rs2.getString(1));
                }else
                {
                    soldQuantity = 0;
                }
                ResultSet rs3 = stmt2.executeQuery("SELECT totalRevenue FROM temp_manufacturer WHERE mID="+mID);
                rs3.next();
                int oldRevenue = Integer.parseInt(rs3.getString(1));
                int pPrice_int = Integer.parseInt(pPrice);
                int newRevenue = oldRevenue + pPrice_int * soldQuantity;
                stmt2.executeUpdate("UPDATE temp_manufacturer SET totalRevenue="+newRevenue+" WHERE mID="+mID);
            }
            rs = stmt.executeQuery("SELECT mID, mName, totalRevenue FROM temp_manufacturer ORDER BY totalRevenue DESC");
            while (rs.next())
            {
                String mID = rs.getString(1);
                String mName = rs.getString(2);
                String totalRevenue = rs.getString(3);
                System.out.println("| "+mID+" | "+mName+" | "+totalRevenue+" |");
            }
            System.out.println("End of Query");
            System.out.println();
            stmt.executeUpdate("DROP TEMPORARY TABLE temp_manufacturer"); 
        }catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.out.println();
        }
    }
    
    public static void showN_mostPopularPart(Statement stmt, String displayNum, Connection conn)
    {
        try
        {
            System.out.println("| Part ID | Part Name | No. of Transaction |");
            stmt.executeUpdate("CREATE TEMPORARY TABLE temp_part SELECT * FROM Part");
            //stmt.executeQuery("SELECT * INTO temp_part FROM Part");
            stmt.executeUpdate("ALTER TABLE temp_part ADD numOfTransaction INT DEFAULT 0");
            ResultSet rs = stmt.executeQuery("SELECT pID FROM Part");
            Statement stmt2 = conn.createStatement();
            while (rs.next())
            {
                String pID = rs.getString(1);
                ResultSet rs2 = stmt2.executeQuery("SELECT count(*) FROM Transaction "
                + "WHERE pID="+pID);
                rs2.next();
                String numOfTransaction = rs2.getString(1);
                stmt2.executeUpdate("UPDATE temp_part SET numOfTransaction="+numOfTransaction+" WHERE "
                + "pID="+pID);
                
            }
            rs = stmt.executeQuery("SELECT pID, pName, numOfTransaction FROM temp_part "
            + "ORDER BY numOfTransaction DESC LIMIT "+displayNum);
            while (rs.next())
            {
                String pID = rs.getString(1);
                String pName = rs.getString(2);
                String numOfTransaction = rs.getString(3);
                int numOfTransaction_int = Integer.parseInt(numOfTransaction);
                if (numOfTransaction_int>0)
                {
                    System.out.println("| "+pID+" | "+pName+" | "+numOfTransaction+" |");
                }
            }
            System.out.println("End of Query");
            System.out.println();
            stmt.executeUpdate("DROP TEMPORARY TABLE temp_part");
        }catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.out.println();
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
                    if (administratorChoice == 5) {
                        System.out.println();
                        continue;
                    }

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
                    if (salespersonChoice == 3) {
                        System.out.println();
                        continue;
                    }

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
                        try
                        {
                            int partID;
                            int salespersonID;
                            //validate partID
                            boolean incorrect_partID=true;
                            
                            System.out.print("Enter The Part ID: ");
                            partID = Integer.parseInt(in.readLine());
                               
                            ResultSet rs = stmt.executeQuery("SELECT pID FROM Part");
                            while (rs.next())
                            {
                                String tempStr = rs.getString(1);
                                int tempInt = Integer.parseInt(tempStr);
                                if (partID==tempInt)
                                {
                                   incorrect_partID = false;
                                   break;
                                }
                            }
                            
                            //validate salespersonID
                            boolean incorrect_salespersonID = true;
                            
                            System.out.print("Enter The Salesperson ID: ");
                            salespersonID = Integer.parseInt(in.readLine());
                                
                            rs = stmt.executeQuery("SELECT sID FROM Salesperson");
                            while (rs.next())
                            {
                                String tempStr = rs.getString(1);
                                int tempInt = Integer.parseInt(tempStr);
                                if (salespersonID==tempInt)
                                {
                                    incorrect_salespersonID = false;
                                    break;
                                }
                            }
                            if (!incorrect_partID && !incorrect_salespersonID)
                            {
                                performTransaction(stmt,partID,salespersonID);
                            }else if(!incorrect_partID)
                            {
                                System.out.println("Invalid Salesperson ID!");
                                System.out.println();
                            }else if (!incorrect_salespersonID)
                            {
                                System.out.println("Invalid Part ID!");
                                System.out.println();
                            }else
                            {
                                System.out.println("Invalid Part ID and Salesperson ID!");
                                System.out.println();
                            }
                        }catch(Exception e){
                            System.err.println("Error: " + e);
                            System.out.println();
                        }
                    }

                    else {
                        System.out.println("Invalid input. Please try again.");
                        System.out.println();
                    }
                }
                
                else if (menuChoice == 3) {
                    printManagerMenu();
                    System.out.print("Enter Your Choice: ");
                    int managerChoice = Integer.parseInt(in.readLine());
                    if (managerChoice==5)
                    {
                        System.out.println();
                        continue;
                    }
                    
                    else if (managerChoice==1)
                    {
                        try
                        {
                            System.out.println("Choose ordering:");
                            System.out.println("1. By ascending order");
                            System.out.println("2. By descending order");
                            System.out.print("Choose the list ordering: ");
                            int order = Integer.parseInt(in.readLine());
                            if (order==1 || order==2)
                            {
                                listAllSalespersons(stmt,order);
                            }else
                            {
                                System.out.println("Invalid Input! Please try again.");
                                System.out.println();
                            }
                        }catch(Exception e)
                        {
                            System.err.println("Error: " + e);
                            System.out.println();
                        }
                    }
                    
                    else if (managerChoice==2)
                    {
                        try
                        {
                            System.out.print("Type in the lower bound for years of experience: ");
                            int lowerBound = Integer.parseInt(in.readLine());
                            System.out.print("Type in the upper bound for years of experience: ");
                            int upperBound = Integer.parseInt(in.readLine());
                            if (lowerBound>=0 && upperBound>=lowerBound)
                            {
                                String lowerBoundStr = String.valueOf(lowerBound);
                                String upperBoundStr = String.valueOf(upperBound);
                                countTransaction(stmt,lowerBoundStr,upperBoundStr,conn);
                            }else
                            {
                                System.out.println("Invalid Input! Please try again.");
                            }
                        }catch(Exception e)
                        {
                            System.err.println("Error: " + e);
                            System.out.println();
                        }
                    }
                        
                    else if (managerChoice==3)
                    {
                        try
                        {
                            showTotalRevenue(stmt,conn);
                        }catch(Exception e)
                        {
                            System.err.println("Error: " + e);
                            System.out.println();
                        }
                    }
                    
                    else if (managerChoice==4)
                    {
                        try
                        {
                            System.out.print("Type in the number of parts: ");
                            String displayNum_str = in.readLine();
                            int displayNum_int = Integer.parseInt(displayNum_str);
                            if (displayNum_int>0)
                            {
                                showN_mostPopularPart(stmt,displayNum_str,conn);
                            }else
                            {
                                System.out.println("Invalid Input! Please try again.");
                            }
                        }catch(Exception e)
                        {
                            System.err.println("Error: " + e);
                            System.out.println();
                        }
                            
                    }
                    
                    else
                    {
                        System.out.println("Invalid Input! Please try again.");
                        System.out.println();
                    }
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