package DAO;

import Model.Requisicao;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

public class RequisicaoDAO {

    private static final DataConnection dataConnection = new DataConnection();
    private static final String SELECT = "SELECT * FROM REQUISICAO WHERE STATUS_APROVACAO = E";
    private static final String CREATE = "INSERT INTO REQUISICAO (NOME, MODELO, DESCRICAO, CLASSIFICACAO, LOTE, COR, ID_FUNCIONARIO, SALDO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_PRODUTO = "INSERT INTO PRODUTO (NOME, MODELO, DESCRICAO, CLASSIFICACAO, LOTE, COR, SALDO) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_FUNCIONARIO = "SELECT NOME FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
    private static final String APPROVE = "INSERT INTO REQUISICAO (STATUS_APROVACAO) VALUES (?) WHERE ID_REQUISICAO = ?";

    public static List<Requisicao> readRequisicoes() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Requisicao> requisicoes = new ArrayList<Requisicao>();
        Requisicao requisicao = new Requisicao();

        try {
            con = dataConnection.getConnection();
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                requisicao.setId_requisicao(rs.getInt("ID_REQUISICAO"));
                requisicao.setNome(rs.getString("NOME"));
                requisicao.setModelo(rs.getString("MODELO"));
                requisicao.setDescricao(rs.getString("DESCRICAO"));
                requisicao.setClassificacao(rs.getString("CLASSIFICACAO"));
                requisicao.setLote(rs.getString("LOTE"));
                requisicao.setCor(rs.getString("COR"));
                requisicao.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));
                requisicao.setSaldo(rs.getInt("SALDO"));
                requisicoes.add(requisicao);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataConnection.closeConnection(con, ps, rs);
        }
        for (Requisicao requisicao2 : requisicoes) {
            try {
                con = dataConnection.getConnection();
                ps = con.prepareStatement(FIND_FUNCIONARIO);

                ps.setInt(1, requisicao2.getId_funcionario());

                rs = ps.executeQuery();

                requisicao2.setNome_funcionario(rs.getString("NOME"));
            } catch(SQLException ex) {
                ex.printStackTrace();
            } finally {
                dataConnection.closeConnection(con, ps, rs);
            }
        }
        return requisicoes;
    }
    public static void createRequisicao(@NotNull Requisicao requisicao) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dataConnection.getConnection();
            ps = con.prepareStatement(CREATE);

            ps.setString(1, requisicao.getNome());
            ps.setString(2, requisicao.getModelo());
            ps.setString(3, requisicao.getDescricao());
            ps.setString(4, requisicao.getClassificacao());
            ps.setString(5, requisicao.getLote());
            ps.setString(6, requisicao.getCor());
            ps.setInt(7, requisicao.getId_funcionario());
            ps.setInt(8, requisicao.getSaldo());

            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataConnection.closeConnection(con, ps);
        }
    }
    public static void approveRequisicao(@NotNull Requisicao requisicao) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = dataConnection.getConnection();
            ps = con.prepareStatement(APPROVE);

            ps.setInt(2, requisicao.getId_requisicao());
            ps.setString(1, requisicao.getStatus_aprovacao());

            ps.execute();

            if (requisicao.getStatus_aprovacao().equals("A")) {
                ps = con.prepareStatement(CREATE);
                ps = con.prepareStatement(CREATE_PRODUTO);
                ps.setString(1, requisicao.getNome());
                ps.setString(2, requisicao.getModelo());
                ps.setString(3, requisicao.getDescricao());
                ps.setString(4, requisicao.getClassificacao());
                ps.setString(5, requisicao.getLote());
                ps.setString(6, requisicao.getCor());
                ps.setInt(7, requisicao.getSaldo());
                ps.execute();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            dataConnection.closeConnection(con, ps);
        }
    }
}
