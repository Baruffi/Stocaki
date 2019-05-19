package View;

import javax.swing.*;

public class menuAdm extends JFrame {
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel produtosPanel;
    private JPanel funcionariosPanel;
    private JLabel pcadLabel;
    private JLabel mlistLabel;
    private JLabel rlistLabel;
    private JLabel plistLabel;
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
    private JPanel topmarginPanel;
    private JPanel bottommarginPanel;

    menuAdm() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {

        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon("iconeCaixa.png");
        stocakiLabel.setIcon(icon);
    }

}
