package Game;

import Player.*;
import XMLProcessing.StimResponse;
import XMLProcessing.XMLConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static final String BLUE = "BLUE";
    static final String RED = "RED";
    static final String ASSASSIN = "ASSASSIN";
    static final String INNOCENT = "INNOCENT";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";


    StimResponse sr;

    Player currentPlayer;
    int red;
    int blue;

    Random random = new Random();

    public static Scanner reader;




    public Game(){
        reader  = new Scanner(System.in);

        System.out.println("Converting XML");
        this.sr = new XMLConverter().getSr();
        System.out.println("Converted XML");

//        Player BlueMaster = new Human("B1", BLUE, true, reader);
        Player BlueMaster = new AI("B1", BLUE, true, sr);
        Player BlueSpy = new Human("B2", BLUE, false, reader);
//        Player RedMaster = new Human("R1", RED, true, reader);
        Player RedMaster = new AI("R1", RED, true, sr);
        Player RedSpy = new Human("R2", RED, false, reader);

        LinkedList<Player> turnOrder = new LinkedList<>();
//        ArrayList<Player> AturnOrder = new ArrayList<>();

        boolean blueStarts = true;//random.nextInt(2) == 1; todo: remove this
        if (blueStarts){
            blue = 9;
            red = 8;
            turnOrder.add(BlueMaster);
            turnOrder.add(BlueSpy);
            turnOrder.add(RedMaster);
            turnOrder.add(RedSpy);
        }
        else {
            blue = 8;
            red = 9;
            turnOrder.add(RedMaster);
            turnOrder.add(RedSpy);
            turnOrder.add(BlueMaster);
            turnOrder.add(BlueSpy);
        }

        Board board = new Board(blueStarts, sr);

        while (blue > 0 && red > 0) {

            Player p1 = turnOrder.remove();
            Player p2 = turnOrder.remove();

            System.out.println(p1 + "'s Turn!");
            Hint hint = p1.getNextHint(board);
            System.out.println(p2 + "'s Turn!");
            Guess guess = p2.getNextGuess(hint, board);

            int guesses = hint.getAmount();
            ArrayList<String> words = guess.words;

            int size = words.size();
            for (int i = 0; i < size; i++){

                if (guesses < 1){
                    break;
                }
                else {
                    guesses--;
                }

                if (words.size() > 0) {

                    String guessedWord = words.remove(0);

                    if (StringUtils.isNotBlank(guessedWord)) {

                        Card card = board.getCard(guessedWord);

                        if (card.type.equals(RED)||card.type.equals(BLUE)) {
                            assignScore(card);
                            if (card.type.equals(p1.getType())) {
                                //do nothing
                                board.flipCard(guessedWord);
                            }
                            else {
                                board.flipCard(guessedWord);
                                break;
                            }
                        }
                        if (card.type.equals(ASSASSIN)){
                            if (p1.getType().equals(RED)){
                                System.out.println("BLUE WINS!");
                            }
                            else {
                                System.out.println("RED WINS!");
                            }
                            board.flipCard(guessedWord);
                            return;//todo: finish game
                        }
                        if (card.type.equals(INNOCENT)){
                            System.out.println("Innocent Card");
                            board.flipCard(guessedWord);
                            break;
                        }
                    }
                }
            }

            turnOrder.add(p1);
            turnOrder.add(p2);

        }


    }


    public Guess takeTurn(Player master, Player spy, Board board){
        System.out.println(master + "'s Turn!");
        Hint hint = master.getNextHint(board);
        System.out.println(spy + "'s Turn!");
        Guess guess = spy.getNextGuess(hint, board);
        return guess;
    }

    public void assignScore(Card card){
        if (card.type.equals(RED)){
            System.out.println(ANSI_RED + "Red Card" + ANSI_RESET);
            red--;
        }
        else if (card.type.equals(BLUE)){
            System.out.println(ANSI_BLUE + "Blue Card" + ANSI_RESET);
            blue--;
        }
        else{
            throw new IllegalArgumentException("Invalid Player type");
        }
    }

    public boolean checkScores(){
        if (red < 1 && blue < 1){
            System.out.println("DRAW?!");
            return true;
        }
        if (red < 1){
            System.out.println(ANSI_RED + "RED WINS!" + ANSI_RESET);
            return true;
        }
        if (blue < 1){
            System.out.println(ANSI_BLUE + "BLUE WINS!" + ANSI_RESET);
            return true;
        }
        return false;
    }





}