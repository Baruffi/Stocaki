package DAO;

import Model.Requisicao;

import java.sql.*;
import java.util.*;

public class RequisicaoDAO {

    private static final DataConnection dataConnection = new DataConnection();
    private static final String SELECT = "SELECT * FROM REQUISICAO";
    private static final String SELECT_FUNCIONARIO = "SELECT NOME FROM FUNCIONARIO, REQUISICAO WHERE FUNCIONARIO.ID_FUNCIONARIO = REQUISICAO.ID_FUNCIONARIO";

    public static List<Requisicao> displayRequisicoes() {
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
                requisicao.setStatus_aprovacao(rs.getString("STATUS_APROVACAO"));
                requisicao.setNome(rs.getString("NOME"));
                requisicao.setModelo(rs.getString("MODELO"));
                requisicao.setDescricao(rs.getString("DESCRICAO"));
                requisicao.setClassificacao(rs.getString("CLASSIFICACAO"));
                requisicao.setLote(rs.getString("LOTE"));
                requisicao.setCor(rs.getString("COR"));
                requisicao.setSaldo(rs.getInt("SALDO"));
                requisicoes.add(requisicao);
            }
//            ps = con.prepareStatement(SELECT_FUNCIONARIO);
//            rs = ps.executeQuery();
//
//            while(rs.next()) {
//                requisicao.setFuncionario(rs.getString("NOME"));
//            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dataConnection.closeConnection(con, ps, rs);
        }
        return requisicoes;
    }
}
