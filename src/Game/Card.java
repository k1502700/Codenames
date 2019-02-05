package Game;

public class Card {
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

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private String word;
    boolean removed;
    String type;

    public Card(String word, String type) {
        this.word = word;
        this.removed = false;
        this.type = type;
    }

    @Override
    public String toString() {
        if (removed){
            return "";
        }
        if (type == RED){
            return ANSI_RED + word + ANSI_RESET;
        }
        if (type == BLUE){
            return ANSI_BLUE + word + ANSI_RESET;
        }
        if (type == ASSASSIN){
            return ANSI_RESET + word + ANSI_RESET;
        }
        if (type == INNOCENT){
            return ANSI_BLACK + word + ANSI_RESET;
        }
        return word;
    }

    public String playerToString(){
        if (removed){
            return " ";
        }
        return ANSI_BLACK + word + ANSI_RESET;
    }

    public String spyMasterToString(){
        if (removed){
            return " ";
        }
        if (type == RED){
            return ANSI_RED + word + ANSI_RESET;
        }
        if (type == BLUE){
            return ANSI_BLUE + word + ANSI_RESET;
        }
        if (type == ASSASSIN){
            return ANSI_RESET + word + ANSI_RESET;
        }
        if (type == INNOCENT){
            return ANSI_BLACK + word + ANSI_RESET;
        }
        return word;
    }

    public String getWord() {
        if (removed){
            return " ";
        }
        return word;
    }
}
