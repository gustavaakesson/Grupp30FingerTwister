package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.*;

public class Scoreboard {
    private ArrayList<Score> scoreboard;

    public Scoreboard(){
        scoreboard = new ArrayList<>();
        String name;
        int score;
        try {
            BufferedReader bufferedReader = new BufferedReader((new FileReader("/Users/gustavakesson/Documents/GitHub/Grupp30FingerTwister/FingetTwisterGame/ScoreBoard.txt")));
            for(int i = 0; i < bufferedReader.read(); ++i) {
                name = bufferedReader.readLine();
                //  System.out.println("ScoreBoard");
                //   System.out.println(name);

                i++;
                score = Integer.parseInt(bufferedReader.readLine());
                //  System.out.println(score);
                Score newscore = new Score(name, score);
                scoreboard.add(newscore);
                sortScoreBoard();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setNewScore(String name, int score) throws IOException {
        Score newscore = new Score(name, score);
        scoreboard.add(newscore);
        sortScoreBoard();
        writeToFile();
    }
    public void writeToFile() throws IOException {
        String playerName;
        String playerScore;

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/gustavakesson/Documents/GitHub/Grupp30FingerTwister/FingetTwisterGame/ScoreBoard.txt", false));
        for (Score score : scoreboard) {
            playerName = score.getName();
            playerScore = String.valueOf(score.getScore());
            bufferedWriter.write(" " + playerName);
            bufferedWriter.newLine();
            bufferedWriter.write(playerScore);
            bufferedWriter.newLine();

        }
        bufferedWriter.close();
    }
    public void sortScoreBoard(){
        sort(scoreboard);

    }

    public ArrayList<Score> getScoreboard(){
        return scoreboard;
    }


}
