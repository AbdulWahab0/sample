/*
package viseco.sc.model.document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import javax.xml.bind.JAXBException;

public class MarshallUtil {
    public static String getXmlForServiceGraph(ServiceGraph) {

        JAXBContext jaxbContext = null;
        // get xml value
        //String xml = testSuiteProjects.get(0).getEndpoint();
        try {
            jaxbContext = JAXBContext.newInstance(ServiceGraph.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            //StringReader reader = new StringReader(xml);
            // serviceGraph contain all values inside the xml file
            //ServiceGraph serviceGraph = (ServiceGraph) unmarshaller.unmarshal(reader);

        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}*/
