package View;

import javax.swing.*;

public class MenuOpr extends JFrame {
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
    private JPanel rootPanel;

    MenuOpr() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(Framework.ICONE_CAIXA);
        stocakiLabel.setIcon(icon);

        titleLabel.setText("Bem Vindo, " + Framework.getCurrentUser().getNome() + "!");

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.PRODUTOS_OPR);
        Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICAO_OPR);
        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACAO_OPR);

    }
}
