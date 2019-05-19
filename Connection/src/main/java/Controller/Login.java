package Controller;

import java.awt.*;

public class Login {
    public static void main(String[] args) {
        //load login screen as first screen and set default windowSize, ExtendedState and isResizeable
        new View.Login(new Dimension(1370, 795), Frame.NORMAL,true);
    }
}
