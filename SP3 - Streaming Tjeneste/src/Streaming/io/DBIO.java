package streaming.io;

import streaming.io.IO;
import streaming.users.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

class DBConnector {
    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/world";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Admin!1000";

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

public class DBIO implements IO {
    @Override
    public ArrayList<String> readDataMedia() throws FileNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DBConnector.getConn();
        try {
            stmt = conn.prepareStatement("select * from streaming.film");
            rs = stmt.executeQuery("select * from streaming.film");

        } catch (SQLException e){
            e.printStackTrace();
        }
        ArrayList<String> output = new ArrayList<>();
        try {
            while(rs.next()){
                output.add(rs.getString("name") + ";" + rs.getString("year") + ";" + rs.getString("genre") + ";" + rs.getDouble("rating"));
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return output;
    }

    @Override
    public ArrayList<String> readDataSeries() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DBConnector.getConn();
        try {
            stmt = conn.prepareStatement("select * from streaming.serier");
            rs = stmt.executeQuery("select * from streaming.serier");

        } catch (SQLException e){
            e.printStackTrace();
        }
        ArrayList<String> output = new ArrayList<>();
        try {
            while(rs.next()){
                output.add(rs.getString("name") + ";" + rs.getString("year") + ";" + rs.getString("genre") + ";" + rs.getDouble("rating") + ";" + rs.getString("seasons"));
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return output;
    }

    @Override
    public ArrayList<String> readDataUser() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DBConnector.getConn();
        try {
            stmt = conn.prepareStatement("select * from streaming.users");
            rs = stmt.executeQuery("select * from streaming.users");

        } catch (SQLException e){
            e.printStackTrace();
        }
        ArrayList<String> output = new ArrayList<>();
        try {
            while(rs.next()){
                output.add((rs.getString("username").trim() + ";" + rs.getString("password").trim() + ";" + rs.getInt("ID") + ";" + rs.getString("isAdult").trim() + ";" + rs.getString("isAdmin").trim() + ";" + rs.getString("saved") + ";" + rs.getString("watched")).replace("null",""));
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return output;
    }

    @Override
    public void writeDataUser(User currentUser) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DBConnector.getConn();
        try {
            String sql = "update streaming.users set saved = \"" + currentUser.getSavedMedia() + "\", watched = \"" + currentUser.getWatchedMedia() + "\"";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}