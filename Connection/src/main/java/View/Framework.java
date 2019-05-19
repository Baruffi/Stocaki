package View;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Framework {

    enum VIEW {FUNCIONARIOADM, FUNCIONARIOSADM, MOVIMENTACOESADM, PRODUTOADM, PRODUTOSADM, REQUISICOESADM}

    static final Color GREEN = new Color(72,180,80);
    static final Color SOFTGREEN = new Color(116,206,119);
    static final Color SELECTED = new Color(100,160,100);

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
            final BufferedImage image = ImageIO.read(new File("menuBackground.jpg"));

            final JPanel imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };

            imagePanel.setPreferredSize(size);
            panel.setPreferredSize(size);

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
            e.printStackTrace();

            panel.setPreferredSize(size);

            frame.setContentPane(panel);

            setCurrentFrame(frame);
            setCurrentPanel(panel);
        }
        frame.pack();
        frame.setLocationRelativeTo(callerFrame);
        frame.setResizable(RESIZEABLE);
        frame.setExtendedState(extendedState);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    static void addToMenu(@NotNull final JPanel panel, final JFrame self, final VIEW target) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(Framework.SELECTED);
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            case REQUISICOESADM:
                new RequisicoesAdm();
                self.dispose();
                break;
            default:
                System.out.println("Tela Inválida!");
                break;
        }
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
