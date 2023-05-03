package streaming.io.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnector {
    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/streaming";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Over4you!";

    public static Connection getConn() {
        Connection conn = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
            //STEP 5: Clean-up environment
            //rs.close();
            //stmt.close();
            //conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }
}