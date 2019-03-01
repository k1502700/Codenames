package XMLProcessing;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.Comparator;

@XStreamAlias("response")
public class Response implements Comparator<Response>, Comparable<Response>{
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

    public double getMathematicalRatio(Stimulus s){
        return ((double) n)/s.getAll();
    }

    public int compareTo(Response r) {

        if (getRatio() == r.getRatio()){
            return 0;
        }
        else if (getRatio() > r.getRatio()){
            return -1;
        }
        else {
            return 1;
        }

    }
    @Override
    public int compare(Response o1, Response o2) {
        return (int) Math.round((o1.getRatio() - o2.getRatio())*100);
    }
}
