package Player;

import Game.Board;
import Game.Card;
import Game.Guess;
import Game.Hint;

import XMLProcessing.Response;
import XMLProcessing.StimResponse;
import XMLProcessing.Stimulus;
import java.util.ArrayList;
import java.util.Collections;

public class AI extends Player{
    static final String BLUE = "BLUE";
    static final String RED = "RED";
    static final String ASSASSIN = "ASSASSIN";
    static final String INNOCENT = "INNOCENT";

    static final String VALUE = "VALUE";
    static final String COMBO = "COMBO";
    static final String ALTERNATECOMBO = "ALTERNATECOMBO";

    StimResponse sr;
    String lastHint = "";
    String strategy = "";

    public AI(String name, String type, boolean isSpymaster, StimResponse sr) {
        this.name = name;
        this.isSpymaster = isSpymaster;
        this.type = type;
        this.sr = sr;
        this.strategy = COMBO;
    }
    public AI(String name, String type, boolean isSpymaster, StimResponse sr, String strategy) {
        this.name = name;
        this.isSpymaster = isSpymaster;
        this.type = type;
        this.sr = sr;
        this.strategy = strategy;
    }

    @Override
    public Hint getNextHint(Board board) {

//        board.printSpyMaster();
        System.out.print("Generating Hint... : ");

        Double score = 0.0;

        ArrayList<Card> cardList = board.getAllCards();
        ArrayList<String> wordList = board.getAllWords();
        ArrayList<Stimulus> stimList = new ArrayList<>();
        ArrayList<Double> scoreList = new ArrayList<>();
        ArrayList<Integer> amountlist = new ArrayList<>();

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
            amountlist.add(0);
        }

        for (String s: wordList){
            s.hashCode();//
        }

        for (int i = 0; i < stimList.size(); i++){
            score = 0.0;
            ArrayList<Double> tempScores = new ArrayList<>();
            for (Response response: stimList.get(i).getResponses()){
                if (wordList.contains(response.getWord())){
                    double temp = getMultiplier(board.getCard(response.getWord())) * response.getMathematicalRatio(stimList.get(i));
                    if (strategy.equals(COMBO) && temp >= 0.25){
                        temp = 0.25;
                    }
                    if (strategy.equals(ALTERNATECOMBO)){
                        temp = Math.sqrt(Math.sqrt(temp))/2;
                    }
                    tempScores.add(temp);
                    score += temp;
                    scoreList.set(i, score);
                }
            }
            Collections.sort(tempScores);
            double min = tempScores.get(0);
            Collections.reverse(tempScores);

            for (int j = 0; j < tempScores.size(); j++){

                if (tempScores.get(j) >= Math.abs(min) && tempScores.get(j) > 0){
                    amountlist.set(i, amountlist.get(i) + 1);
                }
                else {
                    break;
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

        if (amountlist.get(bestHint) < 1){
            amountlist.set(bestHint, 1);
        }
        if (amountlist.get(secondBestHint) < 1){
            amountlist.set(secondBestHint, 1);
        }






        //todo: amount -- should be the amount of positives that are abs() larger than the negatives
        //todo: mi van ha csak olyan asszociacio van amiben az assassin is benne van?
        if (lastHint.equals(stimList.get(bestHint).getWord())){
            amount = amountlist.get(secondBestHint);
            lastHint = stimList.get(secondBestHint).getWord();
            System.out.println(stimList.get(secondBestHint).getWord());
//            lastHint = stimList.get(secondBestHint).getWord(); //todo: do i want to keep it - blacklist last incorrect guess
            return new Hint(stimList.get(secondBestHint).getWord(), board, amount);
        }
        else {
            amount = amountlist.get(bestHint);
            lastHint = stimList.get(bestHint).getWord();
            System.out.println(stimList.get(bestHint).getWord());
            return new Hint(stimList.get(bestHint).getWord(), board, amount);
        }
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
            case ASSASSIN: multiplier = -10;
                break;
            case INNOCENT: multiplier = -0.75;
                break;
            default:  multiplier = 1;
                System.out.println("Incorrect card type provided to AI");
                break;
        }

        return multiplier;
    }


    public Guess getNextGuess(Hint hint, Board board){
        ArrayList<String> wordList = new ArrayList<>();
        Stimulus stimulus;

        try {
            stimulus = sr.getStimulus(hint.getWord());
        }
        catch (IllegalArgumentException e){
            System.out.println(e);
            return new Guess(wordList);
        }

        ArrayList<Response> responseList = stimulus.getResponses();
        Collections.sort(responseList);

        for (Response r: responseList){
            try {
                wordList.add(board.getCard(r.getWord()).getWord());
            }
            catch (IllegalArgumentException e){
                //do nothing
            }
        }

        if (wordList.size() < 1){
            throw new IllegalArgumentException("Error creating new Guess");
        }
//        wordList.add()
        return new Guess(wordList);
    }
}
