package View;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Framework {

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
