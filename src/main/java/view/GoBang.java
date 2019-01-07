package view;

import view.board.Board;
import view.board.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class GoBang extends JFrame {


    public GoBang() {

        //获取屏幕大小 TODO
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screentWidth = screenSize.width;
        int height = screenSize.height;

        setLocationByPlatform(true);

        Image img = new ImageIcon("icon.gif").getImage();

        JMenuBar menuBar = new JMenuBar();
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        //禁止改变窗口大小
        setResizable(false);
        add(new Board());
        add(new ButtonPanel(),BorderLayout.SOUTH);
        pack();

    }

    public void start(){

    }



    public static void main(String[] args) {
        GoBang goBang = new GoBang();
        goBang.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goBang.setSize(800, 900);
        goBang.setVisible(true);
        goBang.setBackground(Color.pink);
    }

}
