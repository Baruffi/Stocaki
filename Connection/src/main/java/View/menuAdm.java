package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class menuAdm extends JFrame {
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

    menuAdm() {
        initComponents();
        Framework.setup(this, menuPanel);

        //iteratividade do menu e transições de tela
        mlistPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mlistPanel.setBackground(Framework.SELECTED);
                mlistPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mlistPanel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });
        rlistPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rlistPanel.setBackground(Framework.SELECTED);
                rlistPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rlistPanel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });
        plistPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                plistPanel.setBackground(Framework.SELECTED);
                plistPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                plistPanel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });
        pcadPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pcadPanel.setBackground(Framework.SELECTED);
                pcadPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                pcadPanel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });
        flistPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                flistPanel.setBackground(Framework.SELECTED);
                flistPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                flistPanel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });
        fcadPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fcadPanel.setBackground(Framework.SELECTED);
                fcadPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                fcadPanel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }
        });
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon("iconeCaixa.png");
        stocakiLabel.setIcon(icon);
    }

}
