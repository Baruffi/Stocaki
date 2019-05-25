package View;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

class Framework {

    enum VIEW {FUNCIONARIO_ADM, FUNCIONARIOS_ADM, MOVIMENTACOES_ADM, PRODUTO_ADM, PRODUTOS_ADM, REQUISICOES_ADM, PRODUTOS_OPR, REQUISICAO_OPR}

    static final Font TABLE_HEADER = new Font("Segoe UI", Font.BOLD , 24),
                      TABLE_BODY = new Font("Segoe UI", Font.PLAIN, 24);

    static final Color GREEN = new Color(72,180,80),
                       SOFTGREEN = new Color(116,206,119),
                       SELECTED = new Color(100,160,100),
                       SOFTGRAY = new Color(162,162,162);

    static final String ICONE_CAIXA = "imgs/iconeCaixa.png",
                        IMAGEM_LOGIN = "imgs/loginImage.png",
                        ICONE_BUSCA = "imgs/iconeBusca2.png",
                        ICONE_APROVAR = "imgs/icons8-checkmark-26.png",
                        ICONE_APROVAR_VERDE = "imgs/green-checkmark.png",
                        ICONE_DELETAR = "imgs/icons8-delete-26.png",
                        ICONE_DELETAR_VERMELHO = "imgs/red-delete.png",
                        PLANO_DE_FUNDO = "imgs/menuBackground.jpg";

    static final Dimension WINDOW_SIZE = new Dimension(1370, 795);

    static final boolean RESIZEABLE = true;

    private static JFrame currentFrame;
    private static JPanel currentPanel;

    static void setup(@NotNull final JFrame frame, @NotNull final JPanel panel) {
        final JFrame callerFrame = getCurrentFrame();
        final int extendedState = callerFrame.getExtendedState();
        final JPanel callerPanel = getCurrentPanel();
        final Dimension size = callerPanel.getSize();

        try {
            final BufferedImage image = ImageIO.read(new File(PLANO_DE_FUNDO));

            final JPanel imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };

            if (extendedState == JFrame.MAXIMIZED_BOTH || extendedState == JFrame.MAXIMIZED_HORIZ || extendedState == JFrame.MAXIMIZED_VERT) {
                imagePanel.setPreferredSize(WINDOW_SIZE);
                panel.setPreferredSize(WINDOW_SIZE);
                frame.setExtendedState(extendedState);
            } else {
                imagePanel.setPreferredSize(size);
                panel.setPreferredSize(size);
            }

            frame.setContentPane(imagePanel);
            frame.add(panel);

            setCurrentFrame(frame);
            setCurrentPanel(imagePanel);

            imagePanel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    panel.setPreferredSize(imagePanel.getSize());
                }
            });
        } catch (IOException e) {
            System.out.println("Imagem de fundo inválida!");

            if (extendedState == JFrame.MAXIMIZED_BOTH || extendedState == JFrame.MAXIMIZED_HORIZ || extendedState == JFrame.MAXIMIZED_VERT) {
                panel.setPreferredSize(WINDOW_SIZE);
                frame.setExtendedState(extendedState);
            } else {
                panel.setPreferredSize(size);
            }

            frame.setContentPane(panel);

            setCurrentFrame(frame);
            setCurrentPanel(panel);
        }
        frame.pack();
        frame.setLocationRelativeTo(callerFrame);
        frame.setResizable(RESIZEABLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    static void addToMenu(@NotNull final JPanel panel, final JFrame self, final VIEW target) {
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Framework.SELECTED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Framework.SOFTGREEN);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                navigate(self, target);
            }
        });
    }

    private static void navigate(JFrame self, @NotNull VIEW target) {
        switch (target) {
            case REQUISICOES_ADM:
                new RequisicoesAdm();
                self.dispose();
                break;
            case REQUISICAO_OPR:
                new RequisicaoOpr();
                self.dispose();
                break;
            default:
                System.out.println("Tela Inválida!");
                break;
        }
    }

    static void addToForm(@NotNull final JTextField textField, final JSeparator separator, final JLabel label) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (!Character.isLetter(e.getKeyChar()) && !Character.isDigit(e.getKeyChar()) && e.getKeyChar() != ' ') {
                    e.consume();
                }
            }
        });
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (!textField.hasFocus()) {
                    if (separator.getBackground() != Color.RED && separator.getBackground() != Color.ORANGE) {
                        separator.setForeground(GREEN);
                        separator.setBackground(GREEN);
                    }
                    label.setForeground(label.getForeground().darker());
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (!textField.hasFocus()) {
                    if (separator.getBackground() != Color.RED && separator.getBackground() != Color.ORANGE) {
                        separator.setForeground(SOFTGREEN);
                        separator.setBackground(Color.WHITE);
                    }
                    label.setForeground(SOFTGRAY);
                }
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                separator.setForeground(GREEN);
                separator.setBackground(GREEN);
                label.setForeground(label.getForeground().darker());
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                separator.setForeground(SOFTGREEN);
                separator.setBackground(Color.WHITE);
                label.setForeground(SOFTGRAY);
            }
        });
    }

    static void addToForm(@NotNull final JButton button, final JTextField[] textFields, final JSeparator[] separators) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int error = 0;
                super.mouseClicked(e);
                for (int i = 0; i < textFields.length; i++) {
                    if (textFields[i].getText().trim().equals("")) {
                        separators[i].setForeground(Color.RED);
                        separators[i].setBackground(Color.RED);
                        if (error == 2 || error == 3) {
                            error = 3;
                        } else {
                            error = 1;
                        }
                    } else if (textFields[i].getText().toUpperCase().trim().matches(".*CREATE.*|.*DROP.*|.*USE.*|.*SELECT.*|.*INSERT.*|.*UPDATE.*|.*DELETE.*|.*FROM.*|.*INTO.*|.*VALUES.*|.*WHERE.*")) {
                        separators[i].setForeground(Color.ORANGE);
                        separators[i].setBackground(Color.ORANGE);
                        if (error == 1 || error == 3) {
                            error = 3;
                        } else {
                            error = 2;
                        }
                    }
                }
                switch (error) {
                    case 3:
                        JOptionPane.showMessageDialog(button, "Campos de texto vazios e campos de texto com entradas inválidas!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(ICONE_CAIXA));
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(button, "Campos de texto com entradas inválidas!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(ICONE_CAIXA));
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(button, "Campos de texto vazios!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(ICONE_CAIXA));
                        break;
                    default:
                        break;
                }
            }
        });

    }
    @Contract(pure = true)
    static JPanel getCurrentPanel() {
        return currentPanel;
    }

    static void setCurrentPanel(JPanel currentPanel) {
        Framework.currentPanel = currentPanel;
    }

    @Contract(pure = true)
    static JFrame getCurrentFrame() {
        return currentFrame;
    }

    static void setCurrentFrame(JFrame currentFrame) {
        Framework.currentFrame = currentFrame;
    }
}
