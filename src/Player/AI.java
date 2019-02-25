package Player;

import Game.Board;
import Game.Card;
import Game.Guess;
import Game.Hint;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import XMLProcessing.Response;
import XMLProcessing.StimResponse;
import XMLProcessing.Stimulus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.util.ArrayList;

public class AI extends Player{
    static final String BLUE = "BLUE";
    static final String RED = "RED";
    static final String ASSASSIN = "ASSASSIN";
    static final String INNOCENT = "INNOCENT";

    StimResponse sr;
    String lastHint = "";

    public AI(String name, String type, boolean isSpymaster, StimResponse sr) {
        this.name = name;
        this.isSpymaster = isSpymaster;
        this.type = type;
        this.sr = sr;
    }

    @Override
    public Hint getNextHint(Board board) {

        System.out.println("Generating Hint...");

        Double score = 0.0;

        ArrayList<Card> cardList = board.getAllCards();
        ArrayList<String> wordList = board.getAllWords();
        ArrayList<Stimulus> stimList = new ArrayList<>();
        ArrayList<Double> scoreList = new ArrayList<>();

        for (Stimulus stimulus: sr.getStimuli()){
            try {
                if (!wordList.contains(stimulus.getWord())) {
                    for (Response response : stimulus.getResponses()) {
                        if (wordList.contains(response.getWord())) {
                            stimList.add(stimulus);
                            break;
                        }
                    }
                }
            }
            catch (NullPointerException e){
                System.out.println("Exception caught while parsing SR list");
            }
        }



        for (int i = 0; i < stimList.size(); i++) {
            scoreList.add( 0.0);
        }
        for (String s: wordList){
            s.hashCode();//
        }

        for (int i = 0; i < stimList.size(); i++){
            score = 0.0;
            for (Response response: stimList.get(i).getResponses()){
                if (wordList.contains(response.getWord())){

                    score += getMultiplier(board.getCard(response.getWord())) * response.getRatio();
                    scoreList.set(i, score);
                }
            }
        }


        double max = 0.0;
        int bestHint = 0;
        int secondBestHint = 0;
        for (int i = 0; i < stimList.size(); i++) {
            if (scoreList.get(i) > max){
                max = scoreList.get(i);
                secondBestHint = bestHint;
                bestHint = i;
            }
        }

        int amount = 1;


        //todo: amount -- should be the amount of positives that are abs() larger than the negatives


        if (lastHint.equals(stimList.get(bestHint).getWord())){
//            lastHint = stimList.get(secondBestHint).getWord(); //todo: do i want to keep it - blacklist last incorrect guess
            return new Hint(stimList.get(secondBestHint).getWord(), board, amount);
        }
        lastHint = stimList.get(bestHint).getWord();
        return new Hint(stimList.get(bestHint).getWord(), board, amount);

//        return new Hint("", board, 1);
    }


    private double getMultiplier(Card c){
        double multiplier = 1.0;
        double switcher = 1.0;

        if (type.equals(RED)){
            switcher = -1;
        }

        switch (c.getType()){
            case BLUE: multiplier = 1 * switcher;
                break;
            case RED: multiplier = -1 * switcher;
                break;
            case ASSASSIN: multiplier = -1000;
                break;
            case INNOCENT: multiplier = 0;
                break;
            default:  multiplier = 1;
                System.out.println("Incorrect card type provided to AI");
                break;
        }

        return multiplier;
    }


    public Guess getNextGuess(Hint hint, Board board){
        ArrayList<String> wordList = new ArrayList<>();
//        wordList.add()
        return new Guess(wordList);
    }
}
