package XMLProcessing;

import com.thoughtworks.xstream.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLConverter {
    StimResponse sr;

    public XMLConverter() {

        Class<?>[] classes = new Class[] { Stimulus.class, Response.class, StimResponse.class};
        XStream xstream = new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        xstream.processAnnotations(Stimulus.class);
        xstream.processAnnotations(Response.class);
        xstream.processAnnotations(StimResponse.class);

        String input = "";
        try {
            input = readFile("C:\\Users\\Robert\\IdeaProjects\\Codenames\\src\\eatStimResp.xml", StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }


        StimResponse sr = (StimResponse) xstream.fromXML(input);

        this.sr = sr;
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public StimResponse getSr() {
        return sr;
    }
}






