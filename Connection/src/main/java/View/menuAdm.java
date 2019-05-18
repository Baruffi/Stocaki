package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class menuAdm extends JPanel {
    private JPanel menuPanel;
    private JTable table;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JTabbedPane menuTabs;
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
    private JScrollPane tableScroll;
    private JPanel sidePanel;

    private BufferedImage image;

    public menuAdm() {
        try {
            image = ImageIO.read(new File("ativo-1.png"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}
