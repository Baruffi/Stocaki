package DAO;

import Model.Produto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProdutoDAO {

    public void create (Produto p) {

        Connection con = DataConnection.getConnection(con);
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("INSERT INTO PRODUTO (NOME,MODELO,DESCRICAO,CLASSIFICACAO,LOTE,COR,SALDO,ID_ARMAZEM) VALUES (?,?,?,?,?,?,?,?)");

            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getModelo());
            stmt.setString(3,p.getDescricao());
            stmt.setString(4,p.getClassificacao());
            stmt.setString(5,p.getLote());
            stmt.setString(6,p.getCor());
            stmt.setInt(7,p.getSaldo());
            stmt.setInt(8,p.getId_armazem());


            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Erro ao salvar!"+ex);
        }


    }

// -------------------------------------------------------------------------

    public void update(Produto p){

        Connection con = DataConnection.getConnection(con);
        PreparedStatement stmt = null;

        try{
            stmt = con.prepareStatement("UPDATE PRODUTO SET NOME = ?,MODELO = ?,DESCRICAO = ?,CLASSIFICACAO = ?,LOTE = ?,COR = ?,SALDO = ?,ID_ARMAZEM = ?  WHERE ID_PRODUTO = ?");

            stmt.setString(1,p.getNome());
            stmt.setString(2,p.getModelo());
            stmt.setString(3,p.getDescricao());
            stmt.setString(4,p.getClassificacao());
            stmt.setString(5,p.getLote());
            stmt.setString(6,p.getCor());
            stmt.setInt(7,p.getSaldo());
            stmt.setInt(8,p.getId_armazem());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar!"+ex);
        }

    }

    // -------------------------------------------------------------------------

    public void delete(Produto p) {

        Connection con=DataConnection.getConnection(con);
        PreparedStatement stmt =null;

        try{
            stmt = con.prepareStatement("DELETE FROM PRODUTO WHERE ID_PRODUTO = ?");

            stmt.setInt(1,p.getId_produto());


            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir"+ex);
        }

    }

    // -------------------------------------------------------------------------
    public void procurar (Produto p) {

        Connection con = DataConnection.getConnection(con);
        PreparedStatement stmt =null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?");

            stmt.setInt(1,p.getId_produto());

            rs = stmt.executeQuery();

            while(rs.next()) {

            }



            JOptionPane.showMessageDialog(null, "Encontrado com sucesso!");
        }

        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Encontrar"+ex);
        }


    }




}
