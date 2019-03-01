package Game;

import XMLProcessing.StimResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    public static final String ANSI_RESET = "\u001B[0m";

    static final String BLUE = "BLUE";
    static final String RED = "RED";
    static final String ASSASSIN = "ASSASSIN";
    static final String INNOCENT = "INNOCENT";

    Card[][] board = new Card[5][5];
    int red;
    int blue;
    int assassin;
    int innocent;
    String starter;
    StimResponse sr;


    public Board(boolean blueStarts, StimResponse sr){

        this.sr = sr;
        initialize(blueStarts);


    }

    public void initialize(boolean blueStarts){

        if (blueStarts){
            blue = 9;
            red = 8;
            starter = BLUE;
        }
        else {
            blue = 8;
            red = 9;
            starter = RED;
        }
        assassin = 1;
        innocent= 7;

        WordGenerator wg = new WordGenerator(sr);
        wg.allWordsIncludedCheck();
        ArrayList<String> wordList = wg.getWords(25);

        int counter = 0;
        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j ++){
                board[i][j] = new Card(wordList.get(counter), getNewCardType());
                counter++;
            }
        }
    }

    public String getNewCardType(){
        Random random = new Random();

        ArrayList<Card> cardList = new ArrayList<>();
        for (int i = 0; i < assassin; i++) {
            cardList.add(new Card("X", ASSASSIN));
        }
        for (int i = 0; i < blue; i++){
            cardList.add(new Card("X", BLUE));
        }
        for (int i = 0; i < red; i++){
            cardList.add(new Card("X", RED));
        }
        for (int i = 0; i < innocent; i++){
            cardList.add(new Card("X", INNOCENT));
        }

        String type = cardList.get(random.nextInt(cardList.size())).type;

        switch (type){
            case RED:
                red--;
                break;
            case BLUE:
                blue--;
                break;
            case INNOCENT:
                innocent--;
                break;
            case ASSASSIN:
                assassin--;
                break;
        }

        return type;

    }

    public void flipCard(String word){
        getCard(word).removed = true;
    }

    public Card getCard(String word){
        for (Card[] row : board){
            for (Card card: row){
                if (card.getWord().equals(word)){
                    return card;
                }
            }
        }
        throw new IllegalArgumentException("No such card on the board");
    }

    public ArrayList<Card> getAllCards(){
        ArrayList<Card> cardList = new ArrayList<>();

        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j ++){
                if (!board[i][j].removed){
                    cardList.add(board[i][j]);
                }
            }
        }
        return cardList;
    }

    public ArrayList<String> getAllWords(){
        ArrayList<Card> cardList = getAllCards();
        ArrayList<String> wordList = new ArrayList<>();

        for (Card c: cardList){
            wordList.add(c.getWord());
        }

        return wordList;
    }

    public void printPlayer() {

        for (Card[] row : board){
            for (Card card: row){
                if (StringUtils.isNotBlank(card.getWord())) {
                    System.out.format("%-20s", card.playerToString());
                }
                else {
                    System.out.format("%-19s", ANSI_RESET + " " + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public String getTopLeftWord(){
        return board[0][0].getWord();
    }

    public void printSpyMaster() {
        System.out.println("--------------------------------------------------");
        for (Card[] row : board){
            for (Card card: row){
                if (StringUtils.isNotBlank(card.getWord())){
                    System.out.format("%-20s", card.spyMasterToString());
                }
                else {
                    System.out.format("%-19s", ANSI_RESET + " " + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
    }

    public void print() {
        printSpyMaster();
//            System.out.format("%-20s%-20s%-20s%-20s%-20s\n", row);
    }
}
