package DAO;

import java.sql.*;

class DataConnection {

    private static final String url = "jdbc:mysql://localhost:3306/STOCAKIBD?useTimezone=true&serverTimezone=UTC",
                                user = "root",
                                password = "root",
                                driver = "com.mysql.cj.jdbc.Driver";

    static Connection getConnection() {
        Connection con = null;
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(
                    url, user, password);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        try{
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static void closeConnection(Connection con, PreparedStatement ps) {
        try{
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    static void closeConnection(Connection con) {
        try{
            if (con != null) {
                con.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
