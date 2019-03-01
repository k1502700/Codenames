package XMLProcessing;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;


@XStreamAlias("eat-stimulus-response")
public class StimResponse {
    @XStreamAsAttribute
    int nb;
    @XStreamImplicit
    ArrayList<Stimulus> stimuli = new ArrayList<>();

    public StimResponse(int nb) {
        this.nb = nb;
    }

    public ArrayList<Stimulus> getStimuli(){
        return stimuli;
    }

    public Stimulus getStimulus(String word){
        for (Stimulus stimulus: stimuli){
            if (stimulus.word.equals(word)){
                return stimulus;
            }
        }
        throw new IllegalArgumentException("Stimulus word not found");
    }

//    public ArrayList<Stimulus> getStimuliForResponse(String response){
//        ArrayList<Stimulus> returnList = new ArrayList<>();
//        for (Stimulus stimulus: stimuli){
//            for (Response r: stimulus.getResponses()){
//                if (r.getWord().equals(response)){
//                    returnList.add(stimulus);
//                }
//            }
//
//
//
//
//        }
}
