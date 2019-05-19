package Model;

import org.jetbrains.annotations.Contract;

import javax.swing.*;

public class ViewModel {

    private static JFrame currentFrame;
    private static JPanel currentPanel;

    @Contract(pure = true)
    public static JPanel getCurrentPanel() {
        return currentPanel;
    }

    public static void setCurrentPanel(JPanel currentPanel) {
        ViewModel.currentPanel = currentPanel;
    }

    @Contract(pure = true)
    public static JFrame getCurrentFrame() {
        return currentFrame;
    }

    public static void setCurrentFrame(JFrame currentFrame) {
        ViewModel.currentFrame = currentFrame;
    }
}
