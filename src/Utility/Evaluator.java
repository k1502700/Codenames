package Utility;

public class Evaluator {
    int blueWins = 0;
    int redWins = 0;
    int games = 0;
    int gameEndedWithAssassin = 0;

    public Evaluator(){

    }

    public void redWon(){
        redWins++;
    }

    public void blueWon(){
        blueWins++;
    }

    public void endedWithAssassin(){
        gameEndedWithAssassin++;
    }
}
