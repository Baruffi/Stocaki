package DAO;

import org.jetbrains.annotations.Contract;

import java.sql.*;

public class DataConnection {

    private static final String url = "jdbc:mysql://localhost:3306/stocakiBD?useTimezone=true&serverTimezone=UTC",
                                user = "root",
                                password = "root",
                                driver = "com.mysql.cj.jdbc.Driver";



    public static Connection getConnection(Connection con) {
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
    public void closeConnection(PreparedStatement ps, ResultSet rs, Connection con) {
        try{
            ps.close();
            rs.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
