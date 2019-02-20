package XMLProcessing;

import com.thoughtworks.xstream.*;

public class XMLConverter {

    public XMLConverter() {

        XStream xstream = new XStream();

        xstream.alias("person", Person.class);

        Person joe = new Person("Joe", "Walnes", 1234, 9999);

        String xml = xstream.toXML(joe);

        System.out.println(xml);

        String otherText = "<person>\n" +
                "  <firstname>Joe</firstname>\n" +
                "  <lastname>Buddy</lastname>\n" +
                "  <phone>1234</phone>\n" +
                "  <fax>9999</fax>\n" +
                "</person>";

        System.out.println("-----------------");
        Person newJoe = (Person)xstream.fromXML(otherText);
        System.out.println(newJoe);
    }
}






