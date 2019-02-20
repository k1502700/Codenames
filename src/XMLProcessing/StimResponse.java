package XMLProcessing;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("eat-stimulus-response")
public class StimResponse {
    @XStreamAsAttribute
    int nb;
    @XStreamImplicit
    List stimuli = new ArrayList();

    public StimResponse(int nb) {
        this.nb = nb;
    }
}
