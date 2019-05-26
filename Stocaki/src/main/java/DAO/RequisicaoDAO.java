package DAO;

import Model.Produto;
import Model.Requisicao;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

public class RequisicaoDAO {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    private static final String SELECT = "SELECT * FROM REQUISICAO WHERE STATUS_APROVACAO = 'E'";
    private static final String CREATE = "INSERT INTO REQUISICAO (NOME, MODELO, DESCRICAO, CLASSIFICACAO, LOTE, COR, ID_FUNCIONARIO, SALDO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String APPROVE = "UPDATE REQUISICAO SET STATUS_APROVACAO = ? WHERE ID_REQUISICAO = ?";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Contract(pure = true)
    public RequisicaoDAO() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    }

    public List<Requisicao> readRequisicoes() {
        List<Requisicao> requisicoes = new ArrayList<Requisicao>();

        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                Requisicao requisicao = new Requisicao();
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
            DataConnection.closeConnection(con, ps, rs);
        }
        for (Requisicao requisicao2 : requisicoes) {
            requisicao2.setNome_funcionario(FuncionarioDAO.getNome(requisicao2.getId_funcionario()));
        }
        return requisicoes;
    }
    public void createRequisicao(@NotNull Requisicao requisicao) {
        try {
            con = DataConnection.getConnection();
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
            DataConnection.closeConnection(con, ps);
        }
    }
    public void approveRequisicao(@NotNull Requisicao requisicao) {
        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(APPROVE);

            ps.setInt(2, requisicao.getId_requisicao());
            ps.setString(1, requisicao.getStatus_aprovacao());

            ps.execute();

            if (requisicao.getStatus_aprovacao().equals("A")) {
                Produto produto = new Produto();
                produto.setNome(requisicao.getNome());
                produto.setModelo(requisicao.getModelo());
                produto.setDescricao(requisicao.getDescricao());
                produto.setClassificacao(requisicao.getClassificacao());
                produto.setLote(requisicao.getLote());
                produto.setCor(requisicao.getCor());
                produto.setSaldo(requisicao.getSaldo());
                produto.setId_armazem(requisicao.getId_armazem());
                produtoDAO.createProduto(produto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps);
        }
    }
}
