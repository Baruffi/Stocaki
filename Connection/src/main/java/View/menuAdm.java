package View;

import javax.swing.*;
import java.awt.*;

public class menuAdm extends JFrame {
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel produtosTab;
    private JPanel funcionariosTab;
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

    menuAdm(Dimension windowSize, int extendedState) {
        initComponents();
        Framework.SETUP(this, menuPanel);
        setExtendedState(getExtendedState() | extendedState);
        menuPanel.setPreferredSize(windowSize);
        setVisible(true);
    }

    private void initComponents() {

        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon("iconeCaixa.png");
        stocakiLabel.setIcon(icon);
    }

}
