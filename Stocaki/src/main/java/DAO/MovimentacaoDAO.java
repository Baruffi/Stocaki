package DAO;

import Model.Movimentacao;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

public class MovimentacaoDAO {
    private static final DataConnection dataConnection = new DataConnection();
    private static final String CREATE = "INSERT INTO MOVIMENTACAO (ID_MOVIMENTACAO, DATAEHORA, MOVIMENTACAOTYPE, SALDO, ID_PRODUTO, ID_FUNCIONARIO) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM MOVIMENTACAO";
    //private static final String SELECT = "SELECT * FROM MOVIMENTACAO WHERE ID_PRODUTO = ?";
    private static final String UPDATE = "UPDATE MOVIMENTACAO SET SALDO = ? WHERE ID_PRODUTO = ?";

    public static List<Movimentacao> readMovimentacoes() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
        Movimentacao movimentacao = new Movimentacao();

        try {
            con = dataConnection.getConnection();
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                movimentacao.setDataEHora(rs.getDate("DATAEHORA"));
                movimentacao.setMovimentacaoType(rs.getString("MOVIMENTACAOTYPE"));
                movimentacao.setSaldo(rs.getInt("SALDO"));
                movimentacao.setId_produto(rs.getInt("ID_PRODUTO"));
                movimentacao.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));
                //movimentacao.add(movimentacao);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataConnection.closeConnection(con, ps, rs);
        }

        return movimentacoes;
    }
    public static void doMovimentacao(@NotNull Movimentacao movimentacao) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dataConnection.getConnection();
            ps = con.prepareStatement(CREATE);

            ps.setDate(1, movimentacao.getDataEHora());
            ps.setString(2, movimentacao.getMovimentacaoType());
            ps.setInt(3, movimentacao.getSaldo());
            ps.setInt(4, movimentacao.getId_produto());
            ps.setInt(5, movimentacao.getId_funcionario());

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataConnection.closeConnection(con, ps);
        }
    }
}
