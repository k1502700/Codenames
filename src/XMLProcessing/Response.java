package XMLProcessing;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("response")
public class Response {
    @XStreamAsAttribute
    String word;
    @XStreamAsAttribute
    int n;
    @XStreamAsAttribute
    double r;

    public Response(String word, int n, double r) {
        this.word = word;
        this.n = n;
        this.r = r;
    }

    public String getWord() {
        return word;
    }

    public int getAmount() {
        return n;
    }

    public double getRatio() {
        return r;
    }
}
