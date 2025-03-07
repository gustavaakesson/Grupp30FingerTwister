package view;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import model.Scoreboard;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ScoreBoardViewer extends JFrame implements Runnable {
    public static void main(String[]args){

    }
    private JPanel panel;
    private JTextArea textArea;
    private int count;
    private Scoreboard scoreboard;
    private View view;

    public ScoreBoardViewer(View view) throws HeadlessException {
        this.view = view;
        // super("ScoreBoard");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.panel.add(this.textArea);
        this.setLocationRelativeTo(null);

        try {
            this.setTextArea();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.setSize(250, 25 * this.count);
        this.add(this.panel);
        this.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
    }

    public void setTextArea() throws IOException {
        this.scoreboard = view.getController().getScoreBoard();
        String name;
        String score;
        String textfield = " ";
        for(int i=0; i<scoreboard.getScoreboard().size(); i++){
            name = scoreboard.getScoreboard().get(i).getName();
            System.out.println("ScoreboardPanel");
            System.out.println(name);
            score = String.valueOf(scoreboard.getScoreboard().get(i).getScore());
            System.out.println(score);
            // textfield = textfield + "Name: " + name + "Score: " + score + "\n";
            textfield = textfield + String.format("%12s, %10s", name , score) + "\n";
        }
        this.textArea.setText(textfield);
        System.out.println("TextField" + "\n" + textfield);
    }

    public void run() {
        FileReader fr = null;

        while(true) {
            String str = "";
            this.count = 0;
            try {
                fr = new FileReader("ScoreBoard.txt");
                BufferedReader br = new BufferedReader(fr);

                for (int i = 0; i < br.read(); ++i) {

                    str = str + br.readLine() + "\n";
                    ++this.count;
                }

                fr.close();
                br.close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
            this.setSize(250, 24 * this.count);
            this.textArea.setText(str);

        }
    }
}
