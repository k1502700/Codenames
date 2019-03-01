import Game.Game;
import Utility.Evaluator;
import XMLProcessing.StimResponse;
import XMLProcessing.XMLConverter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Evaluator e = new Evaluator();

        StimResponse sr = new XMLConverter().getSr();
        while (true) {
            new Game(new Scanner(System.in), sr, e);
        }
//        new XMLConverter();
    }
}
