package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JButton loginButton;
    private JPanel loginPanel;
    private JPasswordField passField;
    private JTextField userField;
    private JPanel bodyPanel;
    private JLabel stocakiLabel;
    private JPanel imagePanel;
    private JPanel fieldsPanel;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel imageLabel;

    public static void main(String[] args) {
        new Login();
    }

    Login() {
        initComponents();

        loginPanel.setPreferredSize(Framework.WINDOW_SIZE);
        setContentPane(loginPanel);

        Framework.setCurrentFrame(this);
        Framework.setCurrentPanel(loginPanel);

        pack();
        setLocationRelativeTo(null);
        setResizable(Framework.RESIZEABLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        //transicao de tela
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuAdm();
                dispose();
            }
        });
    }

    private void initComponents() {
        ImageIcon icon = new ImageIcon("loginImage.png");
        imageLabel.setIcon(icon);
        imageLabel.setText("");
    }
}