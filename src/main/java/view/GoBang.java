package view;

import model.ChessManager;
import model.PieceColor;
import model.role.AI;
import model.role.Player;
import model.role.Role;
import model.role.RoleManager;
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
        pack();
    }

    public void start(){
        Board board = new Board();
        add(board);
        add(new ButtonPanel(),BorderLayout.SOUTH);

        //初始化棋子模型
        ChessManager.getInstance().init();
        ChessManager.getInstance().addListener(board);

        // 初始化角色
        Role role = new Player(PieceColor.BLACK);
        Role role2 = new AI(PieceColor.WHITE);
        RoleManager.getInstance().init(role,role2);
    }



    public static void main(String[] args) {
        GoBang goBang = new GoBang();
        goBang.start();
        goBang.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goBang.setSize(800, 900);
        goBang.setVisible(true);
        goBang.setBackground(Color.LIGHT_GRAY);

    }

}
