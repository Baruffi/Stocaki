package View;

import javax.swing.*;

public class RequisicoesAdm extends JFrame{
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
    private JPanel plistPanel;
    private JLabel plistLabel;
    private JPanel pcadPanel;
    private JLabel pcadLabel;
    private JPanel fgroupPanel;
    private JPanel funcionariosPanel;
    private JPanel flistPanel;
    private JLabel flistLabel;
    private JPanel fcadPanel;
    private JLabel fcadLabel;
    private JPanel ftitlePanel;
    private JLabel ftitleLabel;

    RequisicoesAdm() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon("iconeCaixa.png");
        stocakiLabel.setIcon(icon);

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOESADM);
        // Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOESADM); DISABLED!!!
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOSADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTOADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOSADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIOADM);
    }

}
