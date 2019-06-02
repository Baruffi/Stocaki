package DAO;

import DAO.ProdutoDAO;

import Model.Movimentacao;
import Model.Produto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

public class MovimentacaoDAO {
    ProdutoDAO produtoDAO = new ProdutoDAO();

    private static final DataConnection dataConnection = new DataConnection();
    private static final String CREATE = "INSERT INTO MOVIMENTACAO (DATAEHORA, MOVIMENTACAOTYPE, SALDO, ID_PRODUTO, ID_FUNCIONARIO) VALUES (now(), ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM MOVIMENTACAO WHERE SALDO >=0";
    //private static final String SELECT = "SELECT * FROM MOVIMENTACAO WHERE ID_PRDUTO = ?";
    private static final String UPDATE = "UPDATE MOVIMENTACAO SET SALDO = ? WHERE ID_PRODUTO = ?";
    private static final String SETDATA = "UPDATE MOVIMENTACAO SET SALDO = ? WHERE ID_PRODUTO = ?";



    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Contract(pure = true)
    public MovimentacaoDAO() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    }

    public List<Movimentacao> readMovimentacaos() {
        List<Movimentacao> movimentacao = new ArrayList<Movimentacao>();

        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                Movimentacao movimentacoes = new Movimentacao();
                movimentacoes.setDataEHora(rs.getDate("DATAEHORA"));
                movimentacoes.setMovimentacaoType(rs.getString("MOVIMENTACAOTYPE"));
                movimentacoes.setSaldo(rs.getInt("SALDO"));
                movimentacoes.setId_produto(rs.getInt("ID_PRODUTO"));
                movimentacoes.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));
                movimentacao.add(movimentacoes);

            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps, rs);
        }

        return movimentacao;
    }

    public static void regristateMovimentacao(@NotNull Movimentacao movimentacao) {
        Connection con = null;
        PreparedStatement ps = null;
        ProdutoDAO produtoDAO1 = new ProdutoDAO();

        try {
            Produto produto = new Produto();
            con = DataConnection.getConnection();
            ps = con.prepareStatement(CREATE);

            //ps.setDate(1, movimentacao.getDataEHora());
            ps.setString(1, movimentacao.getMovimentacaoType());
            ps.setInt(2, movimentacao.getSaldo());
            ps.setInt(3, movimentacao.getId_produto());
            ps.setInt(4, movimentacao.getId_funcionario());

            //não está atualizando o saldo do produto.
            //produtoDAO1.updateSaldo(produto);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps);
        }
    }
}
