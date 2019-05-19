package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Framework {
    static final Dimension WINDOW_SIZE = new Dimension(1370, 795);
    static final int EXTENDED_STATE = Frame.NORMAL;
    static final boolean RESIZEABLE = true;

    static void SETUP(final JFrame frame, final JPanel panel) {
        try {
            final BufferedImage image = ImageIO.read(new File("menuBackground.jpg"));

            final JPanel imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            panel.setPreferredSize(WINDOW_SIZE);
            frame.setContentPane(imagePanel);
            frame.add(panel);

            imagePanel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    panel.setPreferredSize(imagePanel.getSize());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            panel.setPreferredSize(WINDOW_SIZE);
            frame.setContentPane(panel);
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(RESIZEABLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
