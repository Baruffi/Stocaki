package View;

import javax.swing.*;

public class MovimentacaoOpr extends JFrame{
    private JPanel rootPanel;
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JPanel requisicaoPanel;
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JLabel modeloLabel;
    private JTextField modeloField;
    private JLabel descricaoLabel;
    private JTextField descricaoField;
    private JLabel classificacaoLabel;
    private JTextField classificacaoField;
    private JLabel loteLabel;
    private JTextField loteField;
    private JLabel corLabel;
    private JTextField corField;
    private JLabel saldoLabel;
    private JTextField saldoField;
    private JButton requisitarButton;
    private JPanel nomeSPanel;
    private JSeparator nomeSeparator;
    private JPanel descricaoSPanel;
    private JSeparator descricaoSeparator;
    private JPanel LoteSPanel;
    private JSeparator loteSeparator;
    private JPanel SaldoSPanel;
    private JSeparator saldoSeparator;
    private JPanel CorSPanel;
    private JSeparator corSeparator;
    private JPanel ClassificacaoSPanel;
    private JSeparator classificacaoSeparator;
    private JPanel ModeloSPanel;
    private JSeparator modeloSeparator;
    private JPanel sidePanel;
    private JPanel pgroupPanel;
    private JPanel ptitlePanel;
    private JLabel ptitleLabel;
    private JPanel produtosPanel;
    private JPanel rlistPanel;
    private JLabel rlistLabel;
    private JPanel mlistPanel;
    private JLabel mlistLabel;

    MovimentacaoOpr() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(Framework.ICONE_CAIXA);
        stocakiLabel.setIcon(icon);

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACAO_OPR);
        Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICAO_OPR);

        Framework.addToForm(nomeField, nomeSeparator, nomeLabel, Framework.INPUT.ALFANUMERICO);
        Framework.addToForm(modeloField, modeloSeparator, modeloLabel, Framework.INPUT.ALFANUMERICO);
        Framework.addToForm(descricaoField, descricaoSeparator, descricaoLabel, Framework.INPUT.ALFANUMERICO);
        Framework.addToForm(classificacaoField, classificacaoSeparator, classificacaoLabel, Framework.INPUT.ALFANUMERICO);
        Framework.addToForm(loteField, loteSeparator, loteLabel, Framework.INPUT.ALFANUMERICO);
        Framework.addToForm(corField, corSeparator, corLabel, Framework.INPUT.ALFABETICO);
        Framework.addToForm(saldoField, saldoSeparator, saldoLabel,Framework.INPUT.NUMERICO);

        JTextField[] textFields = {nomeField, modeloField, descricaoField, classificacaoField, loteField, corField, saldoField};
        JSeparator[] separators = {nomeSeparator, modeloSeparator, descricaoSeparator, classificacaoSeparator, loteSeparator, corSeparator, saldoSeparator};
        //JLabel[] labels = {nomeLabel, modeloLabel, descricaoLabel, classificacaoLabel, loteLabel, corLabel, saldoLabel};

        Framework.submitForm(requisitarButton,textFields,separators, Framework.CONTROLLER.REQUISICOES);
    }

}

