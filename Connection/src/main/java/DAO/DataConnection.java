package DAO;

import org.jetbrains.annotations.Contract;

import java.sql.*;

public class DataConnection {

    private static final String url = "jdbc:mysql://localhost:3306/saptBD?useTimezone=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    @Contract(pure = true)
    public DataConnection(){ }

    public Connection getConnection(Connection con) {

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
