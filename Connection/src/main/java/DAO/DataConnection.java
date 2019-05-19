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
        } finally {
            return con;
        }
    }
    void closeConnection(PreparedStatement ps, ResultSet rs, Connection con) {
        try{
            ps.close();
            rs.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
