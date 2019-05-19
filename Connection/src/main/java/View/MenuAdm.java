package View;

import javax.swing.*;

public class MenuAdm extends JFrame {
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel funcionariosPanel;
    private JLabel flistLabel;
    private JLabel fcadLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JPanel sidePanel;
    private JPanel ptitlePanel;
    private JLabel ptitleLabel;
    private JPanel ftitlePanel;
    private JLabel ftitleLabel;
    private JPanel rlistPanel;
    private JPanel mlistPanel;
    private JPanel plistPanel;
    private JPanel pcadPanel;
    private JPanel flistPanel;
    private JPanel fcadPanel;
    private JLabel rlistLabel;
    private JLabel mlistLabel;
    private JLabel plistLabel;
    private JLabel pcadLabel;
    private JPanel produtosPanel;
    private JPanel pgroupPanel;
    private JPanel fgroupPanel;

    MenuAdm() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon("iconeCaixa.png");
        stocakiLabel.setIcon(icon);

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.DISABLED);
        Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOESADM);
        Framework.addToMenu(plistPanel,this, Framework.VIEW.DISABLED);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.DISABLED);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.DISABLED);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.DISABLED);
    }

}
