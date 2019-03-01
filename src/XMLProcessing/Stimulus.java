package XMLProcessing;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("stimulus")
public class Stimulus {
    @XStreamAsAttribute
    String word;
    @XStreamAsAttribute
    int all;
    @XStreamAsAttribute
    int diff;
    @XStreamImplicit
    List responses = new ArrayList();

    public Stimulus(String word, int all, int diff) {
        this.word = word;
        this.all = all;
        this.diff = diff;
    }

    public void addResponse(Response r){
        responses.add(r);
    }

    public int getAll() {
        return all;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Response> getResponses() {
        return (ArrayList<Response>)responses;
    }
}
