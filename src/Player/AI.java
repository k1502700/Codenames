package Player;

import Game.Board;
import Game.Guess;
import Game.Hint;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.util.ArrayList;

public class AI extends Player{

    public AI(String name, String type, boolean isSpymaster) {
        this.name = name;
        this.isSpymaster = isSpymaster;
        this.type = type;

        readXML();

    }

    public Guess getNextGuess(Hint hint, Board board){
        ArrayList<String> wordList = new ArrayList<>();
//        wordList.add()
        return new Guess(wordList);
    }


    private void readXML(){
        Document doc = null;

        try {
            doc = parseXML("/Users/robert/Git/Cribbage/Codenames/src/eatStimResp.xml");
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (doc != null){
            Element element = doc.getElementById("8211");
            System.out.println(element);
            System.out.println();
        }
    }

    private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(filePath);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
