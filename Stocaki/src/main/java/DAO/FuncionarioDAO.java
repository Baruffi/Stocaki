package DAO;

import org.jetbrains.annotations.Contract;

import java.sql.*;

public class FuncionarioDAO {
    private static final String GET_NOME = "SELECT NOME FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Contract(pure = true)
    public FuncionarioDAO() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    }

    public static String getNome(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String resultado = "";

        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(GET_NOME);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                resultado = rs.getString("NOME");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps, rs);
        }
        return resultado;
    }
}
