package Utility;

public class Evaluator {
    int blueWins = 0;
    int redWins = 0;
    int games = 0;
    double blueWinRatio = 1;
    int gameEndedWithAssassin = 0;

    public Evaluator(){

    }

    private void gameOver(){
        games++;
        blueWinRatio = (double) blueWins / (double) games;
    }

    public void redWon(){
        gameOver();
        redWins++;
    }

    public void blueWon(){
        gameOver();
        blueWins++;
    }

    public void endedWithAssassin(){
        gameOver();
        gameEndedWithAssassin++;
    }
}
