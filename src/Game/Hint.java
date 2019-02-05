package Game;

public class Hint {
    String word;
    Board board;
    int amount;

    public Hint(String word, Board board, int amount) {
        this.word = word;
        this.board = board;
        this.amount = amount;
    }

    public String getWord() {
        return word;
    }

    public int getAmount() {
        return amount;
    }
}
