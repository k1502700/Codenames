package Player;

import Game.Board;
import Game.Guess;
import Game.Hint;

import java.util.ArrayList;

public class Player {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";


    boolean isSpymaster;
    String name;
    String type;


    public Guess getNextGuess(Hint hint, Board board){
        return new Guess(new ArrayList<String>());
    }

    public Hint getNextHint(Board board){
        return new Hint("", board, 1);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type.equals("RED")){
            return ANSI_RED + name + ANSI_RESET;
        }
        if (type.equals("BLUE")){
            return ANSI_BLUE + name + ANSI_RESET;
        }
        return name;
    }
}
