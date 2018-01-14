package view.board;

import control.GameControl;

import javax.swing.*;

public class ButtonPanel extends JPanel{
    private JButton startButton = new JButton("Start");
    private JButton backButton = new JButton("Back");
    private JButton nextButton = new JButton("Next");
    private JButton surrenderButton = new JButton("Surrender");

    public ButtonPanel(){
        initButton();
        addButton();
        addListener();
    }


    private void addListener(){
        GameControl.getInstance().addEndListener(() -> {
            startButton.setEnabled(true);
        });
    }

    private void addButton() {
       add(startButton);
       add(backButton);
       add(nextButton);
       add(surrenderButton);

    }

    private void initButton(){
        initBackButton();
        initStartButton();
        initSurrenderButton();
        initNextButton();
    }

    private void initStartButton(){
        startButton.addActionListener(event ->{
            GameControl.getInstance().startGame();
            startButton.setEnabled(false);
        });
    }

    private void initSurrenderButton(){
        surrenderButton.addActionListener(event ->{
            GameControl.getInstance().endGame();
            startButton.setEnabled(true);
        });
    }

    private void initBackButton(){
        backButton.setEnabled(false);
        //TODO
    }

    private void initNextButton(){
        nextButton.setEnabled(false);
        //TODO
    }

}
