package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoOpr extends JFrame {
    private JPanel rootPanel;
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JPanel sidePanel;
    private JPanel pgroupPanel;
    private JPanel ptitlePanel;
    private JLabel ptitleLabel;
    private JPanel produtosPanel;
    private JPanel rlistPanel;
    private JLabel rlistLabel;
    private JPanel mlistPanel;
    private JLabel mlistLabel;
    private JTextField loteField;
    private JTextField saldoField;
    private JTextField nomeField;
    private JTextField modeloField;
    private JTextField classificacaoField;
    private JTextField corField;
    private JLabel nomeLabel;
    private JLabel descricaoLabel;
    private JLabel modeloLabel;
    private JLabel classificacaoLabel;
    private JLabel loteLabel;
    private JLabel corLabel;
    private JLabel saldoLabel;
    private JButton requisitarButton;
    private JPanel requisicaoPanel;
    private JTextField descricaoField;
    private JSeparator nomeSeparator;
    private JSeparator descricaoSeparator;
    private JSeparator loteSeparator;
    private JSeparator saldoSeparator;
    private JSeparator modeloSeparator;
    private JSeparator classificacaoSeparator;
    private JSeparator corSeparator;
    private JPanel nomeSPanel;
    private JPanel descricaoSPanel;
    private JPanel LoteSPanel;
    private JPanel SaldoSPanel;
    private JPanel CorSPanel;
    private JPanel ClassificacaoSPanel;
    private JPanel ModeloSPanel;

    RequisicaoOpr() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(Framework.ICONE_CAIXA);
        stocakiLabel.setIcon(icon);

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.PRODUTOS_OPR);
        //Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICAO_OPR); DISABLED!!!

        Framework.addToForm(nomeField, nomeSeparator, nomeLabel);
        Framework.addToForm(modeloField, modeloSeparator, modeloLabel);
        Framework.addToForm(descricaoField, descricaoSeparator, descricaoLabel);
        Framework.addToForm(classificacaoField, classificacaoSeparator, classificacaoLabel);
        Framework.addToForm(loteField, loteSeparator, loteLabel);
        Framework.addToForm(corField, corSeparator, corLabel);
        Framework.addToForm(saldoField, saldoSeparator, saldoLabel);

        JTextField[] textFields = {nomeField, modeloField, descricaoField, classificacaoField, loteField, corField, saldoField};
        JSeparator[] separators = {nomeSeparator, modeloSeparator, descricaoSeparator, classificacaoSeparator, loteSeparator, corSeparator, saldoSeparator};
        JLabel[] labels = {nomeLabel, modeloLabel, descricaoLabel, classificacaoLabel, loteLabel, corLabel, saldoLabel};
        Framework.addToForm(requisitarButton,textFields,separators);
    }
}
