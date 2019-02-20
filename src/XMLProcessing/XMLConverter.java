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


        XStream xstream = new XStream();

        xstream.processAnnotations(Stimulus.class);
        xstream.processAnnotations(Response.class);
        xstream.processAnnotations(StimResponse.class);
//        xstream.alias("stimulus", Stimulus.class);
//        xstream.alias("response", Response.class);

        Response r1 = new Response("Bee", 6, 0.6);
        Response r2 = new Response("Bear", 3, 0.3);
        Response r3 = new Response("Pooh", 1, 0.1);

        Stimulus honey = new Stimulus("Honey", 10, 3);
        honey.addResponse(r1);
        honey.addResponse(r2);
        honey.addResponse(r3);




        String honeyXml = xstream.toXML(honey);

        System.out.println(honeyXml);

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






