package Player;

import Game.Board;
import Game.Guess;
import Game.Hint;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Player {
    Scanner reader;

    public Human(String name, String type, boolean isSpymaster, Scanner reader){
        this.name = name;
        this.isSpymaster = isSpymaster;
        this.type = type;
        this.reader = reader;
    }

    public Guess getNextGuess(Hint hint, Board board){//TODO: check for repetition in input
        System.out.print("\033[H\033[2J");
        System.out.flush();

        board.printPlayer();

        System.out.println("Hint: " + hint.getWord());
        System.out.println("Amount: " + hint.getAmount());
        System.out.println();

        System.out.println("Enter guessed word: ");
        String guessWord = reader.next();

        guessWord = StringUtils.upperCase(guessWord);
//        System.out.println(guessWord);

        ArrayList<String> returnList = new ArrayList<>();
        try {
            returnList.add(board.getCard(guessWord).getWord());
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid word, please try again!");
            return getNextGuess(hint, board);
        }

        int counter = 1;

        while (true){

            System.out.println("Enter next word or leave \"X\" if no further guesses: ");
            guessWord = reader.next();

            if (guessWord.equals("X") || guessWord.equals("x")){
                break;
            }
            else {
                counter++;

                try {
                    returnList.add(board.getCard(guessWord).getWord());
                }
                catch (IllegalArgumentException e){
                    System.out.println("Invalid word, please try again!");
                    return getNextGuess(hint, board);
                }
            }

        }



        return new Guess(returnList);
    }

    public Hint getNextHint(Board board){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        board.printSpyMaster();

        System.out.println("Enter Hint word: ");
        String hintWord = reader.next();

        int hintAmount = inputHintAmount();

        return new Hint(hintWord, board, hintAmount);
    }




    private int inputHintAmount(){
        System.out.println("Enter Hint amount: ");
        try {
            return Integer.parseInt(reader.next());
        }
        catch (Exception e){
            System.out.println("Error trying to parse hint amount. Integer required.");
            return inputHintAmount();
        }

    }

    public void clear(){
        for (int i = 0; i < 30; i++){
            System.out.println();
        }
    }
}
