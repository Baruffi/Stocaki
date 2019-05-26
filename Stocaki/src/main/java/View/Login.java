package View;

import Model.Funcionario;

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

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                final Funcionario funcionario = new Funcionario();

                funcionario.setId_funcionario(1);
                funcionario.setNome("teste");
                funcionario.setCpf(1);
                funcionario.setCargo("teste");
                funcionario.setCep(1);
                funcionario.setNivel_acesso('A');
                funcionario.setTelefone(1);
                funcionario.setEmail("teste@teste.com");

                Framework.setCurrentUser(funcionario);

                new MenuAdm();

//                funcionario.setId_funcionario(2);
//                funcionario.setNome("teste");
//                funcionario.setCpf(2);
//                funcionario.setCargo("teste");
//                funcionario.setNivel_acesso('O');
//                funcionario.setTelefone(1);
//                funcionario.setEmail("teste@teste.com");
//
//                new MenuOpr();
//
//                Framework.setCurrentUser(funcionario);

                dispose();
            }
        });
    }

    private void initComponents() {
        ImageIcon icon = new ImageIcon(Framework.IMAGEM_LOGIN);
        imageLabel.setIcon(icon);
        imageLabel.setText("");
    }
}