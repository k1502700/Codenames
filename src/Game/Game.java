package Game;

import Player.*;
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

    Player currentPlayer;
    int red;
    int blue;

    Random random = new Random();

    public static Scanner reader;




    public Game(){
        reader  = new Scanner(System.in);

        Player BlueMaster = new Human("B1", BLUE, true, reader);
        Player BlueSpy = new Human("B2", BLUE, false, reader);
        Player RedMaster = new Human("R1", RED, true, reader);
        Player RedSpy = new Human("R2", RED, false, reader);

        LinkedList<Player> turnOrder = new LinkedList<>();
//        ArrayList<Player> AturnOrder = new ArrayList<>();

        boolean blueStarts = random.nextInt(2) == 1;
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

        Board board = new Board(blueStarts);
//        board.print();

        while (blue > 0 && red > 0) {
//            board.print();

            Player p1 = turnOrder.remove();
            Player p2 = turnOrder.remove();

            System.out.println(p1 + "'s Turn!");
            Hint hint = p1.getNextHint(board);
            System.out.println(p2 + "'s Turn!");
            Guess guess = p2.getNextGuess(hint, board);

            int guesses = hint.getAmount();
//            Guess guess = takeTurn(p1, p2, board);
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
            System.out.println("Red Card");
            red--;
        }
        else if (card.type.equals(BLUE)){
            System.out.println("Blue Card");
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
            System.out.println("RED WINS!");
            return true;
        }
        if (blue < 1){
            System.out.println("BLUE WINS!");
            return true;
        }
        return false;
    }





}

//            if (words.size() > 0) {
//
//
//                    for (String word : words) {
//                    if (StringUtils.isNotBlank(word)) {
//
//                    Card card = board.getCard(word);
//                    board.flipCard(word);
//                    if (card.type.equals(RED)||card.type.equals(BLUE)) {
//                    assignScore(card);
//                    if (card.type.equals(p1.getType())) {
//                    //do nothing
//                    }
//                    else {
//                    break;
//                    }
//                    }
//                    if (card.type.equals(ASSASSIN)){
//                    if (p1.getType().equals(RED)){
//                    System.out.println("BLUE WINS!");
//                    }
//                    else {
//                    System.out.println("RED WINS!");
//                    }
//                    return;//todo: finish game
//                    }
//                    if (card.type.equals(INNOCENT)){
//                    System.out.println("Innocent Card");
//                    break;
//                    }
//                    }
//                    if (checkScores()){
//                    return;
//                    }
//                    }
//
//                    }