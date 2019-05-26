package Controller;

import DAO.MovimendacaoDAO;
import Model.Movimentacao;
import View.Framework;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Calendar;
import java.util.List;


public class Movimentacoes {
    public static void fazerMovimentacao(JButton button, @NotNull JTextField[] textFields) {
        //Requisicao requisicao = new Requisicao();
        Movimentacao movimentacao = new Movimentacao();
       /* Calendar calendar = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int i, int i1) {

            }

            @Override
            public void roll(int i, boolean b) {

            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }
        }
        */
        //verificar se a função de determinar hora vai ser aqui ou no bd.
        //movimentacao.setDataEHora();

        movimentacao.setMovimentacaoType("");
        movimentacao.setSaldo(1);
        movimentacao.setId_produto(1);
        movimentacao.setId_funcionario(1);


        //requisicao.setModelo(textFields[1].getText().trim());

        try{
            MovimendacaoDAO.doMovimentacao(movimentacao);
            for (JTextField textField : textFields) {
                textField.setText("");
            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(button, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
        }
    }

}
