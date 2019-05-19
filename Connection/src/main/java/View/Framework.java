package View;

import Model.ViewModel;
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

    static final Dimension WINDOW_SIZE = new Dimension(1370, 795);
    static final boolean RESIZEABLE = true;

    static void setup(@NotNull final JFrame frame, @NotNull final JPanel panel) {
        final JFrame callerFrame = ViewModel.getCurrentFrame();
        final int extendedState = callerFrame.getExtendedState();
        final JPanel callerPanel = ViewModel.getCurrentPanel();
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

            ViewModel.setCurrentFrame(frame);
            ViewModel.setCurrentPanel(imagePanel);

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

            ViewModel.setCurrentFrame(frame);
            ViewModel.setCurrentPanel(panel);
        }
        frame.pack();
        frame.setLocationRelativeTo(callerFrame);
        frame.setResizable(RESIZEABLE);
        frame.setExtendedState(extendedState);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
