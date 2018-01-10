package view;
import javax.swing.*;
import java.awt.*;

public class GoBang extends JFrame{

    public static void main(String[] args) {
        GoBang goBang = new GoBang();
        goBang.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goBang.setSize(600, 600);
        goBang.setVisible(true);
        goBang.setBackground(Color.pink);
        goBang.add(new Board());
    }

}
