package Controller;

import DAO.RequisicaoDAO;
import Model.Requisicao;
import View.Framework;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

public class Requisicoes {
    public static void fazerRequisicao(JButton button, @NotNull JTextField[] textFields) {
        Requisicao requisicao = new Requisicao();
        requisicao.setNome(textFields[0].getText().trim());
        requisicao.setModelo(textFields[1].getText().trim());
        requisicao.setDescricao(textFields[2].getText().trim());
        requisicao.setClassificacao(textFields[3].getText().trim());
        requisicao.setLote(textFields[4].getText().trim());
        requisicao.setCor(textFields[5].getText().trim());
        requisicao.setSaldo(Integer.parseInt(textFields[6].getText().trim()));
        //requisicao.setId_funcionario(Funcionario.getId_funcionario); DESCOMENTAR QND IMPLEMENTADO!
        try{
            RequisicaoDAO.createRequisicao(requisicao);
            for (JTextField textField : textFields) {
                textField.setText("");
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(button, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
        }
    }
}
