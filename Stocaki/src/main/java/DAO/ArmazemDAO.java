package DAO;

import Model.Produto;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmazemDAO {
    private static final String SELECT_ID = "SELECT ID_ARMAZEM FROM ARMAZEM";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Contract(pure = true)
    public ArmazemDAO() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    }

    public List<Integer> readIdArmazens() {
        List<Integer> id_armazens = new ArrayList<Integer>();

        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(SELECT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                id_armazens.add(rs.getInt("ID_ARMAZEM"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps, rs);
        }
        return id_armazens;
    }
}
