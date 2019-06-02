package Controller;

import DAO.MovimentacaoDAO;
import Model.Movimentacao;
import View.Framework;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


public class Movimentacoes {
    public static void somar(JButton button, @NotNull JTextField[] textFields) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setMovimentacaoType("E");

        try{
            MovimentacaoDAO.regristateMovimentacao(movimentacao);
            for (JTextField textField : textFields) {
                textField.setText("");
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(button, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
        }
    }

}
