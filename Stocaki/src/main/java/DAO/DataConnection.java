package DAO;

import java.sql.*;

class DataConnection {

    private static final String url = "jdbc:mysql://localhost:3306/stocakiBD?useTimezone=true&serverTimezone=UTC",
                                user = "root",
                                password = "root",
                                driver = "com.mysql.cj.jdbc.Driver";

    Connection getConnection() {
        Connection con = null;
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(
                    url, user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
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
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void closeConnection(Connection con, PreparedStatement ps) {
        try{
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void closeConnection(Connection con) {
        try{
            if (con != null) {
                con.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
