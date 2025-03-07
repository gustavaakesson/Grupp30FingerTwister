package view;

import controller.Controller;
import controller.GameMode2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameModePanel extends JPanel implements ActionListener {
    private View view;
    private JComboBox<String> gameModeChoser;
    //private String [] choices = new String[]{"FingerTwister", "TypingRace"};
    private JButton startButton;
    private int count = 5;
    private Timer secTimer;
    private Controller controller;
    private JLabel scoreboardText;
    private JLabel spaceInBetween;
    public GameModePanel(View view, Controller controller) {
        this.controller = controller;
        this.view = view;
        addAllLabels();
    }

    private void addAllLabels() {
        addScoreboardText();
        addSpaceInbetween();
        addStartButton();
        addSpaceInbetween();
        addSpaceInbetween();
    }


    private void addSpaceInbetween() {
        spaceInBetween = new JLabel();
        spaceInBetween.setPreferredSize(new Dimension(400,100));
        this.add(spaceInBetween);
    }
/*
    private void addGameModeChoser() {
        gameModeChoser = new JComboBox<>(choices);
        gameModeChoser.addActionListener(this);
        gameModeChoser.setPreferredSize(new Dimension(300,100));
        gameModeChoser.setFont(new Font("Font.ITALIC",Font.ITALIC,30));

        JLabel gameModeText = new JLabel("Gamemode:");
        gameModeText.setFont(new Font("Font.ITALIC",Font.ITALIC,30));

        this.add(gameModeText);
        this.add(gameModeChoser);
    }

 */

    private void addStartButton() {
        startButton = new JButton("Start");
        startButton.setFont(new Font("Font.ITALIC",Font.ITALIC,54));
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(300,100));
        this.add(startButton);
    }
    private void addScoreboardText(){
        scoreboardText = new JLabel("Scoreboard");
        scoreboardText.setFont(new Font("Font.ITALIC",Font.ITALIC,54));
        scoreboardText.setPreferredSize(new Dimension(300,100));
        this.add(scoreboardText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            view.getScoreBoardPanel().setVisible(false);
            removeScoreboardText();
            if (Objects.equals(startButton.getText(), "STOP")){
                this.setVisible(false);
                new StartMenu();
            }
        }

        if (e.getSource() == startButton){

            startButton.setText("5");
            secTimer = new Timer(1000,this);
            secTimer.start();
        }

        if (e.getSource() instanceof Timer){
            count--;
            startButton.setText(String.valueOf(count));
            if (count == 0 || count < 0){
                secTimer.stop();
                startButton.setText("STOP");
                // TODO: Istället för att starta ett nytt spel ska man skickas tillbaka till GameModePanel
                // transferFocus används för att ge fokus till GamePanel i fönstret för att den ska lyssna efter keystrokes.
                view.getGamePanel().transferFocus();
                //view.getGamePanel().createKeyboard();
                //view.setGamePanel(new GamePanel(view));
                //view.getController().startGame();
                if (view.getController().isGamemode()){
                    view.getController().setGm2(new GameMode2(view, this.controller));
                }else view.getController().startGame();

            }
        }

/*
        if (e.getSource() == getGameModeChoser()){
            for ( int i = 0; i < choices.length;i++ ){
                if (e.getSource() == "FingerTwister"){
                    JOptionPane.showMessageDialog(null,"You are now playing FingerTwister!");
                }
                if (e.getSource() == "TypingRace"){
                    JOptionPane.showMessageDialog(null,"You are now playing TypingRace!");
                }
            }
        }

 */
    }

    public void removeScoreboardText(){
        this.scoreboardText.setText("");
    }
    public void restoreScoreboardText(){
        this.scoreboardText.setText("Scoreboard");
    }

    public JComboBox<String> getGameModeChoser() {
        return gameModeChoser;
    }

    public void setGameModeChoser(JComboBox<String> gameModeChoser) {
        this.gameModeChoser = gameModeChoser;
    }
}
