package DAO;

import Model.Produto;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;


public class ProdutoDAO {
    private static final String CREATE = "INSERT INTO PRODUTO (NOME, MODELO, DESCRICAO, CLASSIFICACAO, LOTE, COR, SALDO, ID_ARMAZEM) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
}
