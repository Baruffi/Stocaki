package DAO;

import Model.Produto;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;


public class ProdutoDAO {
    private static final String CREATE = "INSERT INTO PRODUTO (NOME, MODELO, DESCRICAO, CLASSIFICACAO, LOTE, COR, SALDO, ID_ARMAZEM) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT = "SELECT * FROM PRODUTO";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Contract(pure = true)
    public ProdutoDAO() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    }

    public void createProduto(@NotNull Produto produto) {
        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(CREATE);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getModelo());
            ps.setString(3, produto.getDescricao());
            ps.setString(4, produto.getClassificacao());
            ps.setString(5, produto.getLote());
            ps.setString(6, produto.getCor());
            ps.setInt(7, produto.getSaldo());
            ps.setInt(8, produto.getId_armazem());
            ps.execute();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps);
        }
    }

    public List<Produto> readProdutos() {
        List<Produto> produto = new ArrayList<Produto>();

        try {
            con = DataConnection.getConnection();
            ps = con.prepareStatement(SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produtos = new Produto();
                produtos.setId_produto(rs.getInt("ID_PRODUTO"));
                produtos.setNome(rs.getString("NOME"));
                produtos.setModelo(rs.getString("MODELO"));
                produtos.setDescricao(rs.getString("DESCRICAO"));
                produtos.setClassificacao(rs.getString("CLASSIFICACAO"));
                produtos.setLote(rs.getString("LOTE"));
                produtos.setCor(rs.getString("COR"));
                produtos.setSaldo(rs.getInt("SALDO"));
                produtos.setId_armazem(rs.getInt("ID_ARMAZEM"));
                produto.add(produtos);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            DataConnection.closeConnection(con, ps, rs);
        }
        /*
        for (Movimentacao movimentacao2 : movimentacao){
            movimentacao2.setId_produto(MovimentacaoDAO.);
        }

        for (Requisicao requisicao2 : requisicoes) {
            requisicao2.setNome_funcionario(FuncionarioDAO.getNome(requisicao2.getId_funcionario()));
        }*/
        return produto;
    }



}
