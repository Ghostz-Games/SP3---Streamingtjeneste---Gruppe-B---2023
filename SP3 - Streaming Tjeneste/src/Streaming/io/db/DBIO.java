package streaming.io.db;

import streaming.io.IO;
import streaming.mediaHandler.Media;
import streaming.users.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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
            } catch (SQLException ignore) {
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
                String watched = rs.getString("watched");
                if(watched != null) {
                    watched = watched.trim();
                }
                if(watched == "null") {
                    watched = "";
                }
                String saved = rs.getString("saved");
                if(saved != null) {
                    saved = saved.trim();
                }
                if(saved == "null") {
                    saved = "";
                }
                output.add((rs.getString("username").trim() + ";" + rs.getString("password").trim() + ";" + rs.getInt("ID") + ";" + rs.getString("isAdult").trim() + ";" + rs.getString("isAdmin").trim() + ";" + saved + ";" + watched));
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
            String sql = "select count(*) from streaming.users where username = \"" + currentUser.getUsername() + "\"";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if(rs.next() && rs.getInt("count(*)") > 0) {
                rs.close();
                sql = "update streaming.users set saved = \"" + currentUser.getSavedMedia() + "\", watched = \"" + currentUser.getWatchedMedia() + "\"";
                stmt = conn.prepareStatement(sql);
                stmt.executeUpdate(sql);
            } else {
                rs.close();
                String watched = "\"";
                String saved = "\"";
                for(int i = 0; i < currentUser.getWatchedMedia().size(); ++i){
                    watched += currentUser.getWatchedMedia().get(i).getName();
                    if(i < currentUser.getWatchedMedia().size()-1){
                        watched += ",";
                    }
                }
                watched += "\"";
                for(int i = 0; i < currentUser.getSavedMedia().size(); ++i){
                    saved += currentUser.getSavedMedia().get(i).getName();
                    if(i < currentUser.getSavedMedia().size()-1){
                        saved += ",";
                    }
                }
                saved += "\"";
                sql = "insert into streaming.users \n" +
                        "(`username`,\n" +
                        "`password`,\n" +
                        "`isAdult`,\n" +
                        "`isAdmin`,\n" +
                        "`watched`,\n" +
                        "`saved`) values (" +
                        "\"" + currentUser.getUsername() + "\",\n" +
                        "\"" + currentUser.getPassword() + "\",\n"  +
                        "\"" + currentUser.isAdult() + "\",\n"  +
                        "\"false\"" + ",\n"  +
                        watched + ",\n"  +
                        saved +
                        ")";
                stmt = conn.prepareStatement(sql);
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}