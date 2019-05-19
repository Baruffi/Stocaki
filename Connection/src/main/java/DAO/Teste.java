package DAO;

import DAO.DataConnection;

import java.sql.*;

public class Teste {

    private static final DataConnection dataConnection = new DataConnection();
    private static final String select = "SELECT * FROM usuario";

    public Teste() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = dataConnection.getConnection(con);
            ps = con.prepareStatement(select);
            rs = ps.executeQuery();
            while (rs.next()) {

                String login = rs.getString("login");
                String senha = rs.getString("senha");

                System.out.println("login : " + login);
                System.out.println("senha: " + senha);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dataConnection.closeConnection(ps, rs, con);
        }
    }
}
